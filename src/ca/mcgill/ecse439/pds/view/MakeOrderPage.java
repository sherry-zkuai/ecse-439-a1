package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import java.awt.*;

import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class MakeOrderPage extends JFrame {
	private static final long serialVersionUID = 2517121556423951520L;

	private PizzaDeliveryManager pdm = PizzaDeliveryManager.getInstance();

	// UI Elements
	private JLabel title;
	private JLabel desc;
	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel AddressLabel;

	
	public MakeOrderPage() {
		initComponents();
	}

	private void initComponents() {
	}
}
