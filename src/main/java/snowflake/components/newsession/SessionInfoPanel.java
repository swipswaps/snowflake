package snowflake.components.newsession;

import snowflake.utils.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class SessionInfoPanel extends JPanel {

    private static final long serialVersionUID = 6679029920589652547L;

    private JTextField inpHostName;
    private JSpinner inpPort;
    private JTextField inpUserName;
    private JPasswordField inpPassword;
    private JTextField inpLocalFolder;
    private JTextField inpRemoteFolder;
    private JTextField inpKeyFile;
    private JButton inpLocalBrowse;
    private JButton inpKeyBrowse;
    private JLabel lblHost, lblPort, lblUser, lblPass, lblLocalFolder,
            lblRemoteFolder, lblKeyFile;
    private SpinnerNumberModel portModel;

    private SessionInfo info;

    public SessionInfoPanel() {
        createUI();
    }

    public void hideFields() {
        for (Component c : this.getComponents()) {
            c.setVisible(false);
        }
    }

    public void showFields() {
        for (Component c : this.getComponents()) {
            c.setVisible(true);
        }
    }

    public boolean validateFields() {
        if (inpHostName.getText().length() < 1) {
            showError(TextHolder.getString("message.NoHost"));
            return false;
        }
        if (inpUserName.getText().length() < 1) {
            showError(TextHolder.getString("message.NoUser"));
            return false;
        }
        return true;
    }

    public void setSessionInfo(SessionInfo info) {
        this.info = info;
        setHost(info.getHost());
        setPort(info.getPort());
        setLocalFolder(info.getLocalFolder());
        setRemoteFolder(info.getRemoteFolder());
        setUser(info.getUser());
        setPassword(info.getPassword() == null ? new char[0]
                : info.getPassword().toCharArray());
        setKeyFile(info.getPrivateKeyFile());
    }

    private void setHost(String host) {
        inpHostName.setText(host);
    }

    private void setPort(int port) {
        portModel.setValue(port);
    }

    private void setUser(String user) {
        inpUserName.setText(user);
    }

    private void setPassword(char[] pass) {
        inpPassword.setText(new String(pass));
    }

    private void setLocalFolder(String folder) {
        inpLocalFolder.setText(folder);
    }

    private void setRemoteFolder(String folder) {
        inpRemoteFolder.setText(folder);
    }

    private void setKeyFile(String keyFile) {
        inpKeyFile.setText(keyFile);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void createUI() {
        //setBackground(new Color(245,245,245));
        lblHost = new JLabel(TextHolder.getString("host.name"));
        lblHost.setHorizontalAlignment(JLabel.LEADING);
        lblPort = new JLabel(TextHolder.getString("host.port"));
        lblUser = new JLabel(TextHolder.getString("host.user"));
        lblPass = new JLabel(TextHolder.getString("host.pass"));
        lblLocalFolder = new JLabel(TextHolder.getString("host.localdir"));
        lblRemoteFolder = new JLabel(TextHolder.getString("host.remotedir"));
        lblKeyFile = new JLabel(TextHolder.getString("host.keyfile"));

        inpHostName = GraphicsUtils.createTextField(30);// new JTextField(30);
        inpHostName.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                updateHost();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                updateHost();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                updateHost();
            }

            private void updateHost() {
                info.setHost(inpHostName.getText());
            }
        });
        portModel = new SpinnerNumberModel(22, 1, Short.MAX_VALUE, 1);
        portModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                info.setPort((Integer) portModel.getValue());
            }
        });
        inpPort = new JSpinner(portModel);
        inpUserName = GraphicsUtils.createTextField(30);//new JTextField(30);
        inpUserName.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                updateUser();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                updateUser();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                updateUser();
            }

            private void updateUser() {
                info.setUser(inpUserName.getText());
            }
        });

        inpPassword = new JPasswordField(30);
        inpPassword.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                updatePassword();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                updatePassword();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                updatePassword();
            }

            private void updatePassword() {
                info.setPassword(new String(inpPassword.getPassword()));
            }
        });

        inpLocalFolder = GraphicsUtils.createTextField(30);//new JTextField(30);
        inpLocalFolder.getDocument()
                .addDocumentListener(new DocumentListener() {

                    @Override
                    public void removeUpdate(DocumentEvent arg0) {
                        updateFolder();
                    }

                    @Override
                    public void insertUpdate(DocumentEvent arg0) {
                        updateFolder();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent arg0) {
                        updateFolder();
                    }

                    private void updateFolder() {
                        info.setLocalFolder(inpLocalFolder.getText());
                    }
                });

        inpRemoteFolder = GraphicsUtils.createTextField(30);//new JTextField(30);
        inpRemoteFolder.getDocument()
                .addDocumentListener(new DocumentListener() {

                    @Override
                    public void removeUpdate(DocumentEvent arg0) {
                        updateFolder();
                    }

                    @Override
                    public void insertUpdate(DocumentEvent arg0) {
                        updateFolder();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent arg0) {
                        updateFolder();
                    }

                    private void updateFolder() {
                        info.setRemoteFolder(inpRemoteFolder.getText());
                    }
                });

        inpLocalBrowse = new JButton("Browse");
        inpLocalBrowse.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileHidingEnabled(false);
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                inpLocalFolder.setText(jfc.getSelectedFile().getAbsolutePath());
            }
        });

        inpKeyFile = GraphicsUtils.createTextField(30);//new JTextField(30);
        inpKeyFile.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                updateKeyFile();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                updateKeyFile();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                updateKeyFile();
            }

            private void updateKeyFile() {
                info.setPrivateKeyFile(inpKeyFile.getText());
            }
        });

        inpKeyBrowse = new JButton("Browse");// new JButton(TextHolder.getString("host.browse"));
        inpKeyBrowse.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileHidingEnabled(false);
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                inpKeyFile.setText(jfc.getSelectedFile().getAbsolutePath());
            }
        });

        Insets topInset = new Insets(10, 10, 0, 10);
        Insets noInset = new Insets(0, 10, 0, 10);
        Insets noInsetLeft = new Insets(0, 5, 0, 10);
        Insets noInsetRight = new Insets(0, 10, 0, 0);

        GridBagLayout gbl = new GridBagLayout();

        setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = topInset;
        add(lblHost, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = noInset;
        add(inpHostName, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = topInset;
        add(lblPort, c);

        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        c.insets = noInset;
        add(inpPort, c);

        c.gridx = 0;
        c.gridy = 6;
        c.insets = topInset;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        add(lblUser, c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.insets = noInset;
        add(inpUserName, c);

        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 2;
        c.insets = topInset;
        add(lblPass, c);

        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        c.insets = noInset;
        add(inpPassword, c);

        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 2;
        c.insets = topInset;
        add(lblLocalFolder, c);

        c.gridx = 0;
        c.gridy = 13;
        c.gridwidth = 1;
        c.insets = noInsetRight;
        c.weightx = 10;
        add(inpLocalFolder, c);

        c.gridx = 1;
        c.gridy = 13;
        c.gridwidth = 1;
        c.insets = noInset;
        c.weightx = 1;
        c.insets = noInsetLeft;
        add(inpLocalBrowse, c);

        c.gridx = 0;
        c.gridy = 15;
        c.gridwidth = 2;
        c.insets = topInset;
        add(lblRemoteFolder, c);

        c.gridx = 0;
        c.gridy = 16;
        c.gridwidth = 2;
        c.insets = noInset;
        c.weightx = 10;
        add(inpRemoteFolder, c);

        c.gridx = 1;
        c.gridy = 16;
        c.gridwidth = 1;
        c.weightx = 1;
        c.insets = noInsetLeft;

        c.gridx = 0;
        c.gridy = 17;
        c.gridwidth = 2;
        c.insets = topInset;
        add(lblKeyFile, c);

        c.gridx = 0;
        c.gridy = 18;
        c.gridwidth = 1;
        c.insets = noInsetRight;
        c.weightx = 10;
        add(inpKeyFile, c);

        c.gridx = 1;
        c.gridy = 18;
        c.gridwidth = 1;
        c.weightx = 1;
        c.insets = noInsetLeft;
        add(inpKeyBrowse, c);

        c.gridx = 0;
        c.gridy = 19;
        c.gridwidth = 1;
        c.weightx = 5;
        c.weighty = 20;
        add(new JLabel(), c);
    }

}

