package com.csc.fsg.life.pw.web.io;

/**
 * @author ct07492
 */
public class SouceQueryFilter extends QueryFilter {

	private String view;

	/**
	 * Constructor for SouceQueryFilter.
	 */
	public SouceQueryFilter() {
		super();
	}

	/**
	 * Returns the view.
	 * 
	 * @return String
	 */
	public String getView() {
		return view;
	}

	/**
	 * Sets the view.
	 * 
	 * @param view
	 *            The view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * Method toString
	 * 
	 * @return
	 */

	public String toString() {

		StringBuffer sb = new StringBuffer(super.toString());

		sb.append("view: ");
		if (view != null) {
			sb.append(view).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		return sb.toString();
	}

}
