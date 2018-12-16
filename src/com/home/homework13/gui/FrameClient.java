package com.home.homework13.gui;

import com.home.homework13.dao.DaoAuto;
import com.home.homework13.dao.DaoUserOrder;
import com.home.homework13.database.DB;
import com.home.homework13.entity.User;
import com.home.homework13.entity.UserOrder;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameClient extends JFrame {

    private DB db;
    private JPanel panel, panelBackground, panel2;
    private Table table, table2;
    private JScrollPane scroll, scrollClientOrder;
    private JButton back, makeOrder, deleteOrder;
    private User user;
    private JLabel labelTableAuto, labelClientOrders;
    private JSplitPane split1;
    private JMenuBar bar;
    private JMenu menu;
    private JMenuItem item1, item2, item3;


    public FrameClient(DB db, User user){
        this.user = user;   //!!!
        this.db = db;  //создаем подключение к DB
        setSize(400, 700);  //задаем размеры окна
        setTitle("Страница Клиента");  //даем название странице - заголовок
        setLocationRelativeTo(null);  //помещаем страницу в центр
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //по нажатию на крестик страница будет закрываться
        initComponents();  //добавляем все компоенты на окна, компоненты мы создаем ниже
        activation();  //активируем наши компоненты, активацию создаем ниже
        initComponentsDesign();

        setVisible(true);  //делаем окно видимым
    }

    public void initComponents(){
        panelBackground= new JPanel();
        panelBackground.setLayout(new BorderLayout());
        panel = new JPanel();  //инициализируем панельку, на которую мы будем помещать компоненты страницы
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(0, 350));

        bar = new JMenuBar();//
        bar.setPreferredSize(new Dimension(0,18));//

        menu = new JMenu("Выполнить");//

        item1 = new JMenuItem("Авторизация");//
        item2 = new JMenuItem("Регистрация");//
        item3 = new JMenuItem("Выйти");//

        split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, panel, panel2);
        split1.setDividerLocation(360);
        split1.setDividerSize(8);

        back = new JButton("Выйти");  //инициализируем кнопку назад, даем ей название
        back.setPreferredSize(new Dimension(350, 35));  //задем размер кнопке Назад
        makeOrder = new JButton("Заказать");
        makeOrder.setPreferredSize(new Dimension(350, 35));

        deleteOrder = new JButton("Удалить заказ");
        deleteOrder.setPreferredSize(new Dimension(350, 35));

        labelTableAuto = new JLabel("Список автомобилей");
        labelClientOrders = new JLabel("Ваши заказы");

        DaoAuto daoAuto = new DaoAuto(db);  //вызываем класс DaoAuto для дальнейшего использовапния методов в DaoAuto
        table = new Table(daoAuto.getAll());  //создаем таблицу и помещаем в таблицу все содержимое таблицы Auto с помощью метода getAll, который находится в классе DaoAuto
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        scroll = new JScrollPane(table);  //инициализируем scroll и помещаем на него нашу таблицу
        scroll.setPreferredSize(new Dimension(350, 200));  //задаем размер скрола

        DaoUserOrder daoUserOrder = new DaoUserOrder(db);
        table2 = new Table(daoUserOrder.getUserOrder(user.getId()));  //!!!
        table2.getColumnModel().getColumn(0).setMinWidth(0);
        table2.getColumnModel().getColumn(0).setMaxWidth(0);
        scrollClientOrder = new JScrollPane(table2);
        scrollClientOrder.setPreferredSize(new Dimension(350, 200));

        panel.add(back);  //помещаем кнопку назад под таблицей
        panel.add(labelTableAuto);
        panel.add(scroll);  //помещаем скролл на панельку
        panel.add(makeOrder);
        panel2.add(labelClientOrders);
        panel2.add(scrollClientOrder);
        panel2.add(deleteOrder);

        menu.add(item1);//
        menu.add(item2);//
        menu.add(item3);//
        bar.add(menu);//
        setJMenuBar(bar);

        panelBackground.add(split1);

        add(panelBackground);  //добавляем панельку на окно
    }

    public void initComponentsDesign(){
        panel.setBackground(Color.decode("#CC7D5F"));
        panel2.setBackground(Color.decode("#9DFF5E"));
        makeOrder.setBackground(Color.decode("#FFD4C3"));
        back.setBackground(Color.decode("#7F6A62"));
        deleteOrder.setBackground(Color.decode("#CCFFAA"));
        item1.setBackground(Color.decode("#D1FFBD"));
        item2.setBackground(Color.decode("#D1FFBD"));
        item3.setBackground(Color.decode("#7C81B2"));
    }

    public void activation(){
        back.addActionListener(new ActionListener() {  //активируем кнопку Назад
            @Override
            public void actionPerformed(ActionEvent e) {
                new Authorization(db);  //создаем новое окно Авторизации
                dispose();  //закрываем старое окно Клиента
            }
        });
        makeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() != -1){
                    new DaoUserOrder(db).insert(new UserOrder(user.getId(),
                            Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)))));
                    updateTable();
                }
                else{
                    JOptionPane.showMessageDialog(panel, "Ничего не выбрано!", "Empty order", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        deleteOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table2.getSelectedRow() != -1){
                new DaoUserOrder(db).delete(Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))));
                updateTable();
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
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration(db);
                dispose();
            }
        });
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void updateTable(){
        panelBackground.remove(split1);

        panel = new JPanel();  //инициализируем панельку, на которую мы будем помещать компоненты страницы
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(0, 300));

        split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, panel, panel2);
        split1.setDividerLocation(360);
        split1.setDividerSize(8);

        DaoAuto daoAuto = new DaoAuto(db);  //вызываем класс DaoAuto для дальнейшего использовапния методов в DaoAuto
        table = new Table(daoAuto.getAll());  //создаем таблицу и помещаем в таблицу все содержимое таблицы Auto с помощью метода getAll, который находится в классе DaoAuto
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        scroll = new JScrollPane(table);  //инициализируем scroll и помещаем на него нашу таблицу
        scroll.setPreferredSize(new Dimension(350, 200));  //задаем размер скрола

        DaoUserOrder daoUserOrder = new DaoUserOrder(db);
        table2 = new Table(daoUserOrder.getUserOrder(user.getId()));
        table2.getColumnModel().getColumn(0).setMinWidth(0);
        table2.getColumnModel().getColumn(0).setMaxWidth(0);
        scrollClientOrder = new JScrollPane(table2);
        scrollClientOrder.setPreferredSize(new Dimension(350, 200));

        panel.add(back);  //помещаем кнопку назад под таблицей
        panel.add(labelTableAuto);
        panel.add(scroll);  //помещаем скролл на панельку
        panel.add(makeOrder);
        panel2.add(labelClientOrders);
        panel2.add(scrollClientOrder);
        panel2.add(deleteOrder);

        panelBackground.add(split1);

        initComponentsDesign();

        panelBackground.updateUI();
    }
}
