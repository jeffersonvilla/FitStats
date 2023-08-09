package com.fit.util;

import java.awt.GridBagConstraints;

public class Constraints {
	
	public static GridBagConstraints getGridBagConstraints() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		return constraints;
	}
}
