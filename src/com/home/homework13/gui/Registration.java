package com.home.homework13.gui;

import com.home.homework13.dao.DaoUser;
import com.home.homework13.database.DB;
import com.home.homework13.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JFrame {

    private JPanel panelBackground, panel, panel1, panel2;
    private JTextField login;
    private JPasswordField password, password_conf;
    private JLabel loginLabel, passwordLabel, password_confLabel;
    private JButton enter;
    private DB db;
    private JMenuBar menuBar;
    private JMenuItem item1;

    private JCheckBox admin, client, moderator;

    public Registration(DB db){
        this.db = db;
        setSize(400, 250);
        setTitle("Регистрация");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();
        activation();
        initComponentsLocation();
        initComponentsDesign();

        setVisible(true);
    }

    public void initComponents(){
        panel = new JPanel();
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(8, 0));
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(8, 0));
        panelBackground = new JPanel();
        panelBackground.setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(0, 23));

        item1 = new JMenuItem("Назад");

        login = new JTextField(33);
        password = new JPasswordField(33);
        password_conf = new JPasswordField(33);

        admin = new JCheckBox("Админ"); //в работе
        client = new JCheckBox("Клиент");  //в работе
        moderator = new JCheckBox("Модератор");  //в работе

        loginLabel = new JLabel("Логин");
        passwordLabel = new JLabel("Пароль");
        password_confLabel = new JLabel("Повторите пароль");

        enter = new JButton("Регистрироваться");
        enter.setPreferredSize(new Dimension(200, 20));

        panel.add(loginLabel);
        panel.add(login);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(password_confLabel);
        panel.add(password_conf);

        panel.add(admin);
        panel.add(client);
        panel.add(moderator);

        panel.add(enter);

        add(panelBackground);
    }

    public void initComponentsLocation(){
        panelBackground.add(panel, BorderLayout.CENTER);
        panelBackground.add(panel1, BorderLayout.EAST);
        panelBackground.add(panel2, BorderLayout.WEST);

        menuBar.add(item1);

        setJMenuBar(menuBar);
    }

    public void initComponentsDesign(){
        panel.setBackground(Color.WHITE);
        panel1.setBackground(Color.decode("#a6e5ff"));
        panel2.setBackground(Color.decode("#a6e5ff"));

        item1.setBackground(Color.decode("#fff6a6"));

        menuBar.setBackground(Color.decode("#a6e5ff"));
        enter.setBackground(Color.decode("#a6e5ff"));
    }

    public void activation(){
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DaoUser daoUser = new DaoUser(db);
                boolean validationLogin = daoUser.validationLogin(login.getText());
                boolean validationPassword = daoUser.validationPassword(String.valueOf(password.getPassword()));
                if(validationLogin == true && validationPassword == true){
                    User userLoginExist = new User(login.getText());
                    boolean check = daoUser.checkLogin(userLoginExist);
                    if(check == false){
                        boolean passwordsIdentity = daoUser.passwordsIdentity(String.valueOf(password.getPassword()), String.valueOf(password_conf.getPassword()));
                        if(passwordsIdentity == true) {
                            int role = chooseNewUserRole();
                            User newUser = new User(login.getText(), String.valueOf(password.getPassword()), role, 1);
                            daoUser.insert(newUser);
                            dispose();
                            CheckRoleAndEnter(newUser);
                        }else{
                            JOptionPane.showMessageDialog(panel, "Пароли не совпадают", "CHECK PASSWORDS", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(panel, "Логин уже существует", "INSERT NEW LOGIN", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(panel, "Логин и пароль должны содержать больше 2 и меньше 30 символов. Также не должны содержать пробелы.", "INSERT NEW LOGIN OR PASSWORD", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Authorization(db);
                dispose();
            }
        });

    }

    public void CheckRoleAndEnter(User newUser){
        if(newUser != null){
            switch(newUser.getRole()){
                case 1:
                    new FrameAdmin(db);
                    break;
                case 2:
                    new Authorization(db);
                    JOptionPane.showMessageDialog(panel, "Выполните вход в систему, используя ваш логин и пароль", "ENTER LOGIN AND PASSWORD", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3:
                    new FrameModerator(db);
                    break;
            }
        }
    }

    public int chooseNewUserRole(){
        if(admin.isSelected()){
            return 1;
        }
        if(client.isSelected()){
            return 2;
        }
        if(moderator.isSelected()){
            return 3;
        }
        return 0;
    }

}
