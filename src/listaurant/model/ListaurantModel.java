package listaurant.model;

import org.springframework.jdbc.core.RowMapper;

public abstract class ListaurantModel {

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(getClass().getName()).append("[ ");
		for (java.lang.reflect.Method method : getClass().getMethods()) {
			String mName = method.getName();
			String m = null;
			if ("getClass".equals(mName))
				continue;
			try {
				if (mName.startsWith("get"))
					m = mName.substring(3);
				else if (mName.startsWith("is"))
					m = mName.substring(2);
				else
					continue;
				Object o = method.invoke(this, (Object[]) null);
				buf.append(m).append('=').append(o).append(" ");
			} catch (Exception e) {
			}
		}
		buf.append(']');
		
		return buf.toString();
	}

	public abstract RowMapper<? extends ListaurantModel> map();
}
