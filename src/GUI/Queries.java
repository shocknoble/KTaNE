package GUI;

import controllers.Modules;
import main.Main;

import javax.swing.*;
import java.awt.*;



public class Queries extends JFrame {
    JPanel master = new JPanel();

    JLabel serialLabel = new JLabel("Serial Number");
    JLabel batteryLabel = new JLabel("Number of Batteries");
    JTextField serialInput = new JTextField(null,15);
    JSpinner batterySpinner = new JSpinner();
    SpinnerNumberModel batterySpinnerModel = new SpinnerNumberModel();
    JCheckBox car = new JCheckBox("CAR?");
    JCheckBox frk = new JCheckBox("FRK?");

    JButton submitButton = new JButton("Solve Bomb");
    FlowLayout flow = new FlowLayout(5);

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] screens = ge.getScreenDevices();

    Modules mod = new Modules();

    public Queries() {
        super("KTaNE Bomb Info");
        setSize(325,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        System.out.println(screens[0].getDefaultConfiguration().getBounds());
        setLocation(screens[0].getDefaultConfiguration().getBounds().x,screens[0].getDefaultConfiguration().getBounds().y+10);
        batterySpinnerModel.setMinimum(0);
        batterySpinnerModel.setStepSize(1);
        batterySpinner.setModel(batterySpinnerModel);
        batterySpinner.setPreferredSize(new Dimension(40,20));


        add(master);
        master.add(serialLabel);
        master.add(serialInput);
        master.add(batteryLabel);
        master.add(batterySpinner);
        master.add(car);
        master.add(frk);
        master.add(submitButton);
        master.setLayout(flow);
        master.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setVisible(true);
        master.setLocation(screens[0].getDefaultConfiguration().getBounds().x,screens[0].getDefaultConfiguration().getBounds().y+master.getY());
        submitButton.addActionListener(e -> {
            Main.serialNo = serialInput.getText();
            Main.batteries = Integer.valueOf(batterySpinner.getValue().toString());
            Main.car = car.isSelected();
            Main.frk = frk.isSelected();
            Main.solver = new Thread(mod);
            dispose();
            Main.solver.start();

        });

    }

}
