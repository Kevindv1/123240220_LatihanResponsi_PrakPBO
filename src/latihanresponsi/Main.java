package latihanresponsi;

import latihanresponsi.controllers.CandidateController;
import latihanresponsi.models.CandidateDAO;
import latihanresponsi.views.CandidateView;

import javax.swing.SwingUtilities;

/**
 * Identitas:
 * Kelas : IF-C
 * NIM   : 123240220
 * Nama  : Kevin Diaz Varel
 */
public class Main {
    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Inisialisasi View dan Data Access Object
                CandidateView view = new CandidateView();
                CandidateDAO dao = new CandidateDAO();

                // Inisialisasi Controller
                new CandidateController(view, dao);

                // Tampilkan GUI
                view.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error saat menjalankan aplikasi: " + e.getMessage());
            }
        });
    }
}
