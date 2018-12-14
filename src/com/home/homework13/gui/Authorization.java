package com.home.homework13.gui;

import com.home.homework13.dao.DaoUser;
import com.home.homework13.database.DB;
import com.home.homework13.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authorization extends JFrame {

    private JPanel panelBackground, panel, panel1, panel2;
    private JTextField login;
    private JPasswordField password;
    private JLabel loginLabel, passwordlabel;
    private JButton enter;
    private DB db;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem item1, item2;


    public Authorization(DB db){
        this.db = db;
        setSize(300, 190);
        setTitle("Авторизация");
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
        panelBackground = new JPanel();
        panelBackground.setLayout(new BorderLayout());
        panel = new JPanel();
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(5, 0));
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(5, 0));

        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(0, 18));
        menu = new JMenu("Выполнить");

        item1 = new JMenuItem("Регистрация");
        item2 = new JMenuItem("Назад");

        login = new JTextField(25);
        password = new JPasswordField(25);

        loginLabel = new JLabel("Логин");
        passwordlabel = new JLabel("Пароль");

        enter = new JButton("Войти");
        enter.setPreferredSize(new Dimension(180, 40));

        panel.add(loginLabel);
        panel.add(login);
        panel.add(passwordlabel);
        panel.add(password);
        panel.add(enter);

        add(panelBackground);
    }

    public void initComponentsLocation(){
        panelBackground.add(panel, BorderLayout.CENTER);
        panelBackground.add(panel1, BorderLayout.EAST);
        panelBackground.add(panel2, BorderLayout.WEST);

        menu.add(item1);
        menu.add(item2);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    public void initComponentsDesign(){
        panel.setBackground(Color.decode("#999999"));
        panel1.setBackground(Color.decode("#b6b6b6"));
        panel2.setBackground(Color.decode("#b6b6b6"));
        enter.setBackground(Color.decode("#85c888"));
        menuBar.setBackground(Color.decode("#c88585"));

    }

    public void activation(){
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration(db);
                dispose();
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartFrame();
                dispose();
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DaoUser daoUser = new DaoUser(db);
                User user = daoUser.findByLoginAndPassword(login.getText(), String.valueOf(password.getPassword()));
                if (user == null){
                    JOptionPane.showMessageDialog(panel, "Логин или пароль заполнены неверно!", "ACCESS DENIED", JOptionPane.INFORMATION_MESSAGE);
                }
                User user2 = checkDelStatus(user);
                if(user2 != null){
                    CheckRoleAndEnter(user);
                }
            }
        });
    }

   public User checkDelStatus(User user){
        if(user != null){
            switch(user.getDelStatus()){
                case 1:
                    dispose();
                    return user;
                case 2:
                   JOptionPane.showMessageDialog(panel, "Пользователь заблокирован", "ACCESS DENIED", JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(panel, "Пользоваетль удален", "ACCESS DENIED", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(panel, "Пользователь находится в архиве", "ACCESS DENIED", JOptionPane.INFORMATION_MESSAGE);
                    break;

            }
        }
       return null;
   }

   private void CheckRoleAndEnter(User user){
        if(user != null){
            switch(user.getRole()){
                case 1:
                    new FrameAdmin(db);
                    break;
                case 2:
                    new FrameClient(db, user);
                    break;
                case 3:
                    new FrameModerator(db);
                    break;
            }
        }
   }
}
