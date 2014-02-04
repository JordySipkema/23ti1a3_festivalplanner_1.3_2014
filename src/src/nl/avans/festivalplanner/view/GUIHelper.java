package nl.avans.festivalplanner.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class GUIHelper {

	GUIHelper()
	{
		
	}
	
	public JTabbedPane getTabBar()
	{
		JTabbedPane tabBar = new JTabbedPane();
		
		return tabBar;
	}
	
	public JMenuBar getMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File"); // ?eng: File
		JMenu settings = new JMenu("Settings");
		JMenu help = new JMenu("help"); // ?eng: Help
		menuBar.add(menu);
		menuBar.add(settings);
		menuBar.add(help);

		JMenuItem menu_item_close = new JMenuItem("Close"); // ?eng: Close
		menu_item_close.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});

		menu.add(menu_item_close);

		JMenuItem help_about = new JMenuItem("About"); // ?eng: About
		
		help_about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String about = "About"; // ?eng: About
				String aboutMessage = "About message"; // ?eng:
		
				JOptionPane.showMessageDialog(null, aboutMessage, about, 1);

			}
		});

		help.add(help_about);
		
		return menuBar;
	}
	
	/**
	 * GetStatusBar creates a statusbar to add to your frame
	 * @param statusLabel a JLabel with the content that has to be shown in the statusbar
	 * @param panel. provide a panel for determining the size of the StatusBar
	 * @return the StatusBar to display
	 * @author Jack
	 */
	public JPanel getStatusBar(JLabel statusLabel,JPanel panel)
	{
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(panel.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		
		return statusPanel;
	}
}