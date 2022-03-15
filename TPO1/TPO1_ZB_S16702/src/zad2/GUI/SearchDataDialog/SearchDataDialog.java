package zad2.GUI.SearchDataDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import zad2.GUI.SearchDataDialog.ActionListeners.CancelButtonClickActionListener;
import zad2.GUI.SearchDataDialog.ActionListeners.OKButtoonClickActionListener;
import zad2.GUI.SearchDataDialog.Controllers.SearchDataDialogController;
import zad2.GUI.Window.Controllers.WindowController;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class SearchDataDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3719405291349892536L;
	private final JPanel contentPanel = new JPanel();
	private JTextField countryInputField;
	private JTextField cityInputField;
	private JTextField currencyInputField;
	private SearchDataDialogController searchDataDialogController;
	public WindowController windowController;

	/**
	 * Create the dialog.
	 * @param windowController 
	 */
	public SearchDataDialog(WindowController windowController) {
		this.searchDataDialogController = new SearchDataDialogController(this);
		this.windowController = windowController;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblNewLabel_1 = new JLabel("Country name");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel_1);
		}
		{
			countryInputField = new JTextField();
			contentPanel.add(countryInputField);
			countryInputField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("City name");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel);
		}
		{
			cityInputField = new JTextField();
			contentPanel.add(cityInputField);
			cityInputField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Currency code");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel_2);
		}
		{
			currencyInputField = new JTextField();
			contentPanel.add(currencyInputField);
			currencyInputField.setColumns(3);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OKButtoonClickActionListener(this.searchDataDialogController));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new CancelButtonClickActionListener(this.searchDataDialogController));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getCountryInputField() {
		return countryInputField;
	}
	public JTextField getCityInputField() {
		return cityInputField;
	}
	public JTextField getCurrencyInputField() {
		return currencyInputField;
	}
}
