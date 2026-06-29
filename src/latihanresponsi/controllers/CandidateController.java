package latihanresponsi.controllers;

import latihanresponsi.models.*;
import latihanresponsi.views.CandidateView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CandidateController {
    private final CandidateView view;
    private final CandidateDAO dao;
    private int selectedId = -1;
    private List<Candidate> currentCandidates;

    public CandidateController(CandidateView view, CandidateDAO dao) {
        this.view = view;
        this.dao = dao;

        initController();
        loadTableData();
    }

    private void initController() {
        view.getBtnAdd().addActionListener(e -> addCandidate());
        view.getBtnUpdate().addActionListener(e -> updateCandidate());
        view.getBtnDelete().addActionListener(e -> deleteCandidate());
        view.getBtnClear().addActionListener(e -> clearForm());

        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1 && currentCandidates != null && row < currentCandidates.size()) {
                    Candidate selected = currentCandidates.get(row);
                    selectedId = selected.getId();
                    view.getTfName().setText(selected.getName());
                    String roleStr = selected.getRole().equals("Android Developer") ? "Android Dev" : "Web Dev";
                    view.getCbRole().setSelectedItem(roleStr);
                    view.getTfWriting().setText(String.valueOf(selected.getWritingScore()));
                    view.getTfCoding().setText(String.valueOf(selected.getCodingScore()));
                    view.getTfInterview().setText(String.valueOf(selected.getInterviewScore()));
                }
            }
        });
    }

    private void loadTableData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        currentCandidates = dao.getAllCandidates();
        for (Candidate c : currentCandidates) {
            String roleStr = c.getRole().equals("Android Developer") ? "Android Dev" : "Web Dev";
            Object[] row = {
                c.getName(),
                roleStr,
                c.getWritingScore(),
                c.getCodingScore(),
                c.getInterviewScore(),
                String.format("%.2f", c.calculateFinalScore()),
                c.determineStatus()
            };
            model.addRow(row);
        }
    }

    private void addCandidate() {
        try {
            String name = view.getTfName().getText();
            String role = view.getCbRole().getSelectedItem().toString();
            double writing = Double.parseDouble(view.getTfWriting().getText());
            double coding = Double.parseDouble(view.getTfCoding().getText());
            double interview = Double.parseDouble(view.getTfInterview().getText());

            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nama tidak boleh kosong!");
                return;
            }

            Candidate candidate;
            if (role.equals("Android Dev")) {
                candidate = new AndroidDeveloper(0, name, writing, coding, interview);
            } else {
                candidate = new WebDeveloper(0, name, writing, coding, interview);
            }

            dao.addCandidate(candidate);
            JOptionPane.showMessageDialog(view, "Data Berhasil Ditambahkan!");
            clearForm();
            loadTableData();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Nilai harus berupa angka yang valid!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
        }
    }

    private void updateCandidate() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data di tabel terlebih dahulu!");
            return;
        }

        try {
            String name = view.getTfName().getText();
            String role = view.getCbRole().getSelectedItem().toString();
            double writing = Double.parseDouble(view.getTfWriting().getText());
            double coding = Double.parseDouble(view.getTfCoding().getText());
            double interview = Double.parseDouble(view.getTfInterview().getText());

            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nama tidak boleh kosong!");
                return;
            }

            Candidate candidate;
            if (role.equals("Android Dev")) {
                candidate = new AndroidDeveloper(selectedId, name, writing, coding, interview);
            } else {
                candidate = new WebDeveloper(selectedId, name, writing, coding, interview);
            }

            dao.updateCandidate(candidate);
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah!");
            clearForm();
            loadTableData();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Nilai harus berupa angka yang valid!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
        }
    }

    private void deleteCandidate() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data di tabel terlebih dahulu!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.deleteCandidate(selectedId);
            JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus!");
            clearForm();
            loadTableData();
        }
    }

    private void clearForm() {
        view.clearFields();
        selectedId = -1;
    }
}
