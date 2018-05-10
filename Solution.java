import java.util.*;

public class Solution {

	private static TreeMap<Integer, Integer> areas = new TreeMap<Integer, Integer>();
	private static TreeMap<Integer, ArrayList<String>> coordinates = new TreeMap<Integer, ArrayList<String>>();

	public static int getResults() {
		int areasMultipliedMax = 0;
		for (int i = 1; i <= areas.keySet().size(); i++) {
			for (int j = 1; j <= areas.keySet().size(); j++) {

				int areasMultiplied = areas.get(i) * areas.get(j);
				if (areasMultiplied > areasMultipliedMax) {
					boolean notOverlapping = true;
					for (int c = 0; c < coordinates.get(i).size(); c++) {
						if (coordinates.get(j).contains(coordinates.get(i).get(c))) {
							notOverlapping = false;
							break;
						}
					}
					if (notOverlapping) {
						areasMultipliedMax = areasMultiplied;
					}

				}
			}
		}
		return areasMultipliedMax;
	}

	public static void getAllFigures(String[][] grid, int rows, int columns) {
		int counterFigures = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j].equals("G")) {
					counterFigures++;
					int area = 1;
					ArrayList<String> newSingleCoordinates = new ArrayList<String>();
					newSingleCoordinates.add(i + "" + j);
					areas.put(counterFigures, area);
					coordinates.put(counterFigures, newSingleCoordinates);
					int up = i - 1;
					int down = i + 1;
					int left = j - 1;
					int right = j + 1;
					ArrayList<String> newCoordinates = new ArrayList<String>();
					newCoordinates.add(i + "" + j);
					while (true) {

						if (up >= 0 && down < rows && left >= 0 && right < columns) {
							if (grid[up][j].equals("G") && grid[down][j].equals("G") && grid[i][left].equals("G")
									&& grid[i][right].equals("G")) {

								ArrayList<String> addCoordinates = new ArrayList<String>();

								newCoordinates.add(up + "" + j);
								newCoordinates.add(down + "" + j);
								newCoordinates.add(i + "" + left);
								newCoordinates.add(i + "" + right);

								addCoordinates.addAll(newCoordinates);

								counterFigures++;
								area = area + 4;
								areas.put(counterFigures, area);
								coordinates.put(counterFigures, addCoordinates);
							} else {
								break;
							}

						} else {
							break;
						}
						down++;
						up--;
						left--;
						right++;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int rows = reader.nextInt();
		int columns = reader.nextInt();

		String[][] grid = new String[rows][columns];

		for (int i = 0; i < rows; i++) {
			String input = reader.next();
			for (int j = 0; j < columns; j++) {
				grid[i][j] = Character.toString(input.charAt(j));
			}
		}
		getAllFigures(grid, rows, columns);
		System.out.println(getResults());

	}
