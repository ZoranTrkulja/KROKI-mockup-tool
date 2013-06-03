/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kroki.app.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kroki.app.KrokiMockupToolApp;
import kroki.app.utils.StringResource;
import kroki.profil.subsystem.BussinesSubsystem;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Vladan Marsenić (vladan.marsenic@gmail.com)
 */
public class NewPackageDialog extends JDialog {

	BussinesSubsystem owner;
	BussinesSubsystem newSubsystem;
	JPanel content;
	JPanel action;
	JButton okBtn;
	JButton cancelBtn;
	JLabel nameLbl;
	JTextField nameTf;
	JTextField projectTf;
	JLabel projectLbl;

	public NewPackageDialog(JFrame frame, BussinesSubsystem bussinesSubsystem) {
		super(frame);
		this.owner = bussinesSubsystem;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());
		content = new JPanel();
		action = new JPanel();
		this.add(content, BorderLayout.CENTER);
		this.add(action, BorderLayout.SOUTH);
		projectTf = new JTextField(owner.getLabel());
		projectTf.setEnabled(false);
		projectLbl = new JLabel(StringResource.getStringResource("dialog.project.name.label"));


		nameLbl = new JLabel(StringResource.getStringResource("dialog.package.name.label"));
		nameTf = new JTextField(50);

		nameTf.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					okActionPreformed();
				}
			}
		});

		MigLayout layout = new MigLayout("wrap 2");
		content.setLayout(layout);
		content.add(projectLbl);
		content.add(projectTf, "grow, wrap");
		content.add(nameLbl);
		content.add(nameTf);


		okBtn = new JButton(StringResource.getStringResource("dialog.ok.label"));
		okBtn.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				okActionPreformed();
			}
		});
		cancelBtn = new JButton(StringResource.getStringResource("dialog.cancel.label"));
		cancelBtn.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				cancelActionPreformed();
			}
		});
		action.setLayout(new FlowLayout(FlowLayout.CENTER));
		action.add(okBtn);
		action.add(cancelBtn);

	}

	private void okActionPreformed() {
		if (nameTf.getText().equals("")) {
			return;
		}
		//find project in which we want to make package
		BussinesSubsystem project = KrokiMockupToolApp.getInstance().findProject(owner);
		//check if  package with same name exists in that project (in any level)
		BussinesSubsystem pr = KrokiMockupToolApp.getInstance().findPackage(nameTf.getText(), project);
		System.out.println("[PROJECT] " + project.getLabel());
		System.out.println("[OWNER] " + owner.getLabel());
		
		if(pr != null) {
			System.out.println("[PR] " + pr.getLabel());
			JOptionPane.showMessageDialog(NewPackageDialog.this, "Package with specified name allready exists!");
		}else {
			newSubsystem = new BussinesSubsystem(owner);
			newSubsystem.setLabel(nameTf.getText());

			owner.addNestedPackage(newSubsystem);
			this.dispose();
		}
	}

	private void cancelActionPreformed() {
		this.dispose();
	}
}
