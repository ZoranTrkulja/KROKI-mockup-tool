/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kroki.app.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import kroki.app.utils.StringResource;
import kroki.profil.ComponentType;
import kroki.profil.subsystem.BussinesSubsystem;

/**
 *
 * @author Vladan Marsenić (vladan.marsenic@gmail.com)
 */
public class NewProjectDialog extends JDialog {

    BussinesSubsystem newSubystem;
    JPanel content;
    JPanel action;
    private JLabel projectNameLbl;
    private JTextField projectNameTf;
    private JButton okBtn;
    private JButton cancelBtn;

    public NewProjectDialog(Frame owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        content = new JPanel();
        action = new JPanel();
        this.add(content, BorderLayout.CENTER);
        this.add(action, BorderLayout.SOUTH);

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

        projectNameLbl = new JLabel(StringResource.getStringResource("dialog.project.name.label"));
        projectNameTf = new JTextField(50);
        
        projectNameTf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					okActionPreformed();
				}
			}
		});

        content.setLayout(new FlowLayout(FlowLayout.CENTER));
        content.add(projectNameLbl);
        content.add(projectNameTf);
    }

    private void okActionPreformed() {
        newSubystem = new BussinesSubsystem(projectNameTf.getText(), true, ComponentType.MENU, null);
        this.dispose();
    }

    private void cancelActionPreformed() {
        this.dispose();
    }

    public BussinesSubsystem getNewSubystem() {
        return newSubystem;
    }
}