package latihanresponsi.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CandidateView extends JFrame {

    private JTextField tfName;
    private JComboBox<String> cbRole;
    private JTextField tfWriting;
    private JTextField tfCoding;
    private JTextField tfInterview;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    private JTable table;
    private DefaultTableModel tableModel;

    public CandidateView() {
        setTitle("Sistem Rekrutmen PT. OOP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table Panel (Center)
        String[] columnNames = {"Name", "Path", "Writing", "Coding", "Interview", "Score", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Right Panel (East)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(150, 0));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblName = new JLabel("Name");
        lblName.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfName = new JTextField();
        tfName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        tfName.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblName);
        formPanel.add(tfName);
        formPanel.add(Box.createVerticalStrut(10));

        JLabel lblPath = new JLabel("Path");
        lblPath.setAlignmentX(Component.LEFT_ALIGNMENT);
        String[] roles = {"Android Dev", "Web Dev"};
        cbRole = new JComboBox<>(roles);
        cbRole.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        cbRole.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblPath);
        formPanel.add(cbRole);
        formPanel.add(Box.createVerticalStrut(10));

        JLabel lblWriting = new JLabel("Writing");
        lblWriting.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfWriting = new JTextField();
        tfWriting.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        tfWriting.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblWriting);
        formPanel.add(tfWriting);
        formPanel.add(Box.createVerticalStrut(10));

        JLabel lblCoding = new JLabel("Coding");
        lblCoding.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfCoding = new JTextField();
        tfCoding.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        tfCoding.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblCoding);
        formPanel.add(tfCoding);
        formPanel.add(Box.createVerticalStrut(10));

        JLabel lblInterview = new JLabel("Interview");
        lblInterview.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfInterview = new JTextField();
        tfInterview.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        tfInterview.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(lblInterview);
        formPanel.add(tfInterview);
        formPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(formPanel);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(4, 1, 0, 5));
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        rightPanel.add(btnPanel);

        mainPanel.add(rightPanel, BorderLayout.EAST);
        add(mainPanel);
    }

    // Getters for controller access
    public JTextField getTfName() { return tfName; }
    public JComboBox<String> getCbRole() { return cbRole; }
    public JTextField getTfWriting() { return tfWriting; }
    public JTextField getTfCoding() { return tfCoding; }
    public JTextField getTfInterview() { return tfInterview; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void clearFields() {
        tfName.setText("");
        cbRole.setSelectedIndex(0);
        tfWriting.setText("");
        tfCoding.setText("");
        tfInterview.setText("");
        table.clearSelection();
    }
}
