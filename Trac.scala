object Trac {
  def main(args: Array[String]) {
    import scala.xml.XML
    import java.net.URL
    val url = new URL("http://lampsvn.epfl.ch/trac/scala/query?status=assigned&status=new&status=reopened&order=priority&col=id&col=summary&col=status&col=owner&col=type&col=priority&col=component&owner=extempore")
    val ticketList = XML.load(url)
//    for (ticket <- ticketList \\ "a" \\ "@href") println(ticket)
    val ticketUrls = for (link <- ticketList \\ "a" \\ "@href" if link.text.contains("ticket")) yield link.text
    val ticketRssUrls = ticketUrls map (_ + "?format=rss")
    println(ticketRssUrls)
    for {
      ticket <- ticketRssUrls
      text <- XML.load(new URL(ticket)) \\ "description" map (_.text)
    } if (text.contains("Estimate"))
      println(text.substring(text.indexOf("Estimate"), text.indexOf("\n", text.indexOf("Estimate"))))
  }
}
    //val url = new URL("http://adwiki.ad-group.adh/query?status=assigned&status=new&status=reopened&order=priority&owner=rclarkson")
