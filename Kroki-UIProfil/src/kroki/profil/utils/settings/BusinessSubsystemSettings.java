package kroki.profil.utils.settings;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import kroki.intl.Intl;
import kroki.profil.VisibleElement;
import kroki.profil.subsystem.BussinesSubsystem;
import net.miginfocom.swing.MigLayout;

public class BusinessSubsystemSettings extends JPanel implements Settings{

	private static final long serialVersionUID = 1L;


	protected BussinesSubsystem businessSubsystem;
	protected SettingsCreator settingsCreator;
	protected LayoutManager panelLayout;
	protected JLabel lblLabel;
	protected JTextField tfLabel;
	protected JLabel lblLabelToCode;
	protected JCheckBox chLabelToCode;

	public BusinessSubsystemSettings(SettingsCreator settingsCreator){
		panelLayout = new MigLayout("wrap 2,hidemode 3", "[right, shrink][fill, 200]");
		this.settingsCreator = settingsCreator;
		initComponents();
		layoutComponents();
		addActions();
	}

	private void initComponents(){
		lblLabel = new JLabel();
		lblLabel.setText(Intl.getValue("visibleElement.label"));
		tfLabel = new JTextField(30);
		lblLabelToCode = new JLabel();
		lblLabelToCode.setText(Intl.getValue("stfPanelSettings.labelToCode"));
		chLabelToCode = new JCheckBox();
	}

	private void layoutComponents() {
		setLayout(new MigLayout("wrap 2,hidemode 3", "[right, shrink][fill, 200]"));
		add(lblLabel);
		add(tfLabel);
		add(lblLabelToCode);
		add(chLabelToCode);
	}

	public void updateComponents() {
		tfLabel.setText(businessSubsystem.getLabel());
		if (businessSubsystem.nestingPackage() != null){
			remove(lblLabelToCode);
			remove(chLabelToCode);
		}
		else{
			remove(lblLabelToCode);
			remove(chLabelToCode);
			remove(lblLabel);
			remove(lblLabel);
			add(lblLabel);
			add(tfLabel);
			add(lblLabelToCode);
			add(chLabelToCode);
		}
		chLabelToCode.setSelected(businessSubsystem.isLabelToCode());
	}


	private void addActions(){

		tfLabel.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				contentChanged(e);
			}

			public void removeUpdate(DocumentEvent e) {
				contentChanged(e);
			}

			public void changedUpdate(DocumentEvent e) {
				//nista se ne desava
			}

			private void contentChanged(DocumentEvent e) {
				Document doc = e.getDocument();
				String text = "";
				try {
					text = doc.getText(0, doc.getLength());
				} catch (BadLocationException ex) {
				}
				businessSubsystem.setLabel(text);
				updatePreformedIncludeTree();
			}
		});


		chLabelToCode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JCheckBox src = (JCheckBox) e.getSource();
				boolean value = src.isSelected();
				businessSubsystem.setLabelToCode(value);
				System.out.println(businessSubsystem.isLabelToCode());
				updatePreformed();
			}
		});
	}


	@Override
	public void updatePreformed() {
		businessSubsystem.update();
		settingsCreator.settingsPreformed();
	}

	@Override
	public void updatePreformedIncludeTree() {
		businessSubsystem.update();
		settingsCreator.settingsPreformed();
	}

	@Override
	public void updateSettings(VisibleElement visibleElement) {
		this.businessSubsystem = (BussinesSubsystem)visibleElement;
		updateComponents();

	}


}
