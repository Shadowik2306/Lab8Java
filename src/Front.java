import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Objects;

public class Front extends JFrame {
    private JPanel mainPanel;
    private JTextField countryName;
    private JTextField priceCount;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton changeButton;
    private JCheckBox sortCheckBox;
    private JComboBox sortBox2;
    private JCheckBox countryFilterCheckBox;
    private JCheckBox priceFilterCheckBox;
    private JComboBox priceFilterBox1;
    private JCheckBox nameFilterCheckBox;
    private JButton filterButton;
    private JList<Sofa> jlist;
    private JTextField sofaName;
    private JPanel needIdPanel;
    private JComboBox sortBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField gggg;


    SofaList lst = new SofaList();
    int countOfItems = 0;

    Front() {
        super("8 лаба");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setMinimumSize(new Dimension(700, 500));
        jlist.setModel(new DefaultListModel<Sofa>());

        String[] choice_lst = {"Название", "Страна", "Цена"};
        DefaultComboBoxModel<String> model_choice = new DefaultComboBoxModel<>();
        for (int i = 0; i < 3; i++) {
            model_choice.addElement(choice_lst[i]);
        }
        sortBox1.setModel(model_choice);

        String[] reverse_lst = {">", "<"};
        DefaultComboBoxModel<String> model_reverse = new DefaultComboBoxModel<>();
        for (int i = 0; i < 2; i++) {
            model_reverse.addElement(reverse_lst[i]);
        }
        sortBox2.setModel(model_reverse);

        String[] filter_lst = {">", "<", "="};
        DefaultComboBoxModel<String> model_filter = new DefaultComboBoxModel<>();
        for (int i = 0; i < 3; i++) {
            model_filter.addElement(filter_lst[i]);
        }
        priceFilterBox1.setModel(model_filter);


        saveButton.addActionListener(e -> {
            saveSofa();
            clearInput();
        });

        jlist.addListSelectionListener(e -> {
            if (jlist.getSelectedIndex() >= 0) {
                needIdPanel.setBorder(new TitledBorder("id = " + String.valueOf(jlist.getSelectedIndex() + 1)));
                DefaultListModel<Sofa> model = (DefaultListModel<Sofa>) jlist.getModel();
                Sofa sofa = model.getElementAt(jlist.getSelectedIndex());
                sofaName.setText(sofa.name);
                countryName.setText(sofa.country);
                priceCount.setText(String.valueOf(sofa.price));
            }
        });

        deleteButton.addActionListener(e -> {
            if (jlist.getSelectedIndex() >= 0) {
                lst.remove(jlist.getSelectedIndex());
                jlist.setModel(loadModel());
                clearInput();
            }
        });

        changeButton.addActionListener(e -> {
            if (jlist.getSelectedIndex() >= 0) {
                if (!Objects.equals(sofaName.getText(), "") && !Objects.equals(countryName.getText(), "")
                        && isNumeric(priceCount.getText())) {
                    lst.change(jlist.getSelectedIndex(), sofaName.getText(), countryName.getText(), priceCount.getText());
                    jlist.setModel(loadModel());
                    clearInput();
                }
            }
        });

        filterButton.addActionListener(e -> {
            jlist.setModel(loadModel());
            clearInput();
        });
    }

    private void clearInput() {
        needIdPanel.setBorder(new TitledBorder("id = ?"));
        sofaName.setText("");
        countryName.setText("");
        priceCount.setText("");
    }

    public void saveSofa() {
        if (!Objects.equals(sofaName.getText(), "") && !Objects.equals(countryName.getText(), "")
                && isNumeric(priceCount.getText())) {
            lst.insert(sofaName.getText(), countryName.getText(), priceCount.getText());
            jlist.setModel(loadModel());
        }
    }

    private DefaultListModel<Sofa> loadModel () {
        countOfItems = 0;
        SofaList listSave = lst;
        if (sortCheckBox.isSelected()) {
            int n = -1;
            Object item = sortBox1.getSelectedItem();
            if ("Название".equals(item)) {
                listSave = listSave.sortList(0, Objects.requireNonNull(sortBox2.getSelectedItem()).toString());
            } else if ("Страна".equals(item)) {
                listSave = listSave.sortList(1, Objects.requireNonNull(sortBox2.getSelectedItem()).toString());
            } else if ("Цена".equals(item)) {
                listSave = listSave.sortList(2, Objects.requireNonNull(sortBox2.getSelectedItem()).toString());
            }
        }
        System.out.println(listSave.getLst().toString());
        if (nameFilterCheckBox.isSelected()) {
            listSave = listSave.filterList(0, "=", textField1.getText());
        }
        if (countryFilterCheckBox.isSelected()) {
            listSave = listSave.filterList(1, "=", textField1.getText());
        }
        if (priceFilterCheckBox.isSelected()) {
            listSave = listSave.filterList(2, Objects.requireNonNull(priceFilterBox1.getSelectedItem()).toString(), gggg.getText());
        }

        DefaultListModel<Sofa> model_k = new DefaultListModel<Sofa>();
        for (Sofa item : listSave.getLst()) {
            model_k.add(countOfItems, item);
            countOfItems++;
        }
        return model_k;
    }

    public static void main(String[] args) {
        new Front();
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
