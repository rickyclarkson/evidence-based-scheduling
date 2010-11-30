import java.util.*;

/*

E.g., java Main 1,1 2,2 3,3 4,4 10 20 30

This is the perfect estimator; he estimated 1 hour and took one hour, similar for 3 other tasks.  How long will he take for 3 tasks whose times add up to 60?  There's 100% chance he will take 60 hours.

java Main 3,1 3,2 3,5 3 3 3

This is the guy who just always says 3 hours for everything.  How long will it take him to do three tasks, which he estimates at, unsurprisingly, 3 hours each?
*/
class Main {
	public static void main(String[] args) {
		List<Double> velocities = new ArrayList<Double>();
		List<Integer> estimates = new ArrayList<Integer>();

		for (String arg: args)
			if (arg.contains(",")) {
				String[] split = arg.split(",");
				velocities.add((double)Integer.parseInt(split[0]) / Integer.parseInt(split[1]));
			} else
				estimates.add(Integer.parseInt(arg));

		ebs(velocities, estimates);
	}

	public static void ebs(List<Double> velocities, List<Integer> estimates) {
		Random random = new Random();
		TreeMap<Integer, Integer> timeToPercent = new TreeMap<Integer, Integer>();
		for (int a = 0; a < 100; a++) {
			double time = 0;
			for (Integer estimate: estimates)
				time += estimate / velocities.get(random.nextInt(velocities.size()));
			
			int key = (int)time;
			if (timeToPercent.containsKey(key))
				timeToPercent.put(key, timeToPercent.get(key) + 1);
			else
				timeToPercent.put(key, 1);
		}
		for (Map.Entry<Integer, Integer> entry: timeToPercent.entrySet())
			System.out.println("There is a " + entry.getValue() + "% chance of completion in " + entry.getKey() + " hours.");
	}
}
