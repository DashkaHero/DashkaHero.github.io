package listaurant.common;

public class ListaurantUtil {

	public static Integer parseInt(String num) throws ListaurantException {
		Integer iNum = 0;

		try {
			iNum = Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			throw new ListaurantException(nfe.getMessage());
		}

		return iNum;
	}

	public static boolean isNull(String str) {
		boolean isNull = false;

		if (str == null || "".equals(str))
			isNull = true;

		return isNull;
	}
}
