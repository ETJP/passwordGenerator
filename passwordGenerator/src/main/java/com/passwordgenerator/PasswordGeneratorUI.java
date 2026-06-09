package com.passwordgenerator;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.datatransfer.*;

/**
 * Fenêtre principale de l'application.
 * Gère l'interface graphique Swing.
 */
public class PasswordGeneratorUI extends JFrame {

    private JSpinner   spinnerLongueur;
    private JCheckBox  cbMajuscules, cbChiffres, cbSymboles, cbAvance;
    private JTextField champMotDePasse;
    private JLabel     labelNiveau;
    private JButton    btnCopier;

    private final Generateur generateurSimple = new GenerateurSimple();
    private final Generateur generateurAvance = new GenerateurAvance();

    public PasswordGeneratorUI() {
        setTitle("Générateur de Mot de Passe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        construireUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void construireUI() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(20, 25, 20, 25));
        main.setBackground(new Color(245, 245, 250));

        // Titre
        JLabel titre = new JLabel("🔐 Générateur de Mot de Passe");
        titre.setFont(new Font("SansSerif", Font.BOLD, 18));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(new EmptyBorder(0, 0, 15, 0));
        main.add(titre);

        // Longueur
        JPanel panelLongueur = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelLongueur.setBackground(new Color(245, 245, 250));
        panelLongueur.add(new JLabel("Longueur :"));
        spinnerLongueur = new JSpinner(new SpinnerNumberModel(12, 4, 64, 1));
        spinnerLongueur.setPreferredSize(new Dimension(70, 28));
        panelLongueur.add(spinnerLongueur);
        main.add(panelLongueur);

        // Options
        JPanel panelOptions = new JPanel(new GridLayout(4, 1, 4, 4));
        panelOptions.setBackground(new Color(245, 245, 250));
        panelOptions.setBorder(new TitledBorder("Options"));
        cbMajuscules = creerCheckbox("Majuscules (A-Z)", true);
        cbChiffres   = creerCheckbox("Chiffres (0-9)", true);
        cbSymboles   = creerCheckbox("Symboles (!@#...)", true);
        cbAvance     = creerCheckbox("Mode avancé (garantit chaque type)", true);
        panelOptions.add(cbMajuscules);
        panelOptions.add(cbChiffres);
        panelOptions.add(cbSymboles);
        panelOptions.add(cbAvance);
        main.add(panelOptions);
        main.add(Box.createVerticalStrut(12));

        // Bouton Générer
        JButton btnGenerer = new JButton("Générer");
        btnGenerer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGenerer.setBackground(new Color(70, 130, 180));
        btnGenerer.setForeground(Color.WHITE);
        btnGenerer.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnGenerer.setFocusPainted(false);
        btnGenerer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btnGenerer.addActionListener(e -> genererMotDePasse());
        main.add(btnGenerer);
        main.add(Box.createVerticalStrut(12));

        // Résultat + bouton Copier
        JPanel panelResultat = new JPanel(new BorderLayout(8, 0));
        panelResultat.setBackground(new Color(245, 245, 250));
        champMotDePasse = new JTextField();
        champMotDePasse.setEditable(false);
        champMotDePasse.setFont(new Font("Monospaced", Font.BOLD, 15));
        champMotDePasse.setHorizontalAlignment(JTextField.CENTER);
        champMotDePasse.setBackground(Color.WHITE);
        champMotDePasse.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 200)));
        btnCopier = new JButton("Copier");
        btnCopier.setBackground(new Color(108, 117, 125));
        btnCopier.setForeground(Color.WHITE);
        btnCopier.setFocusPainted(false);
        btnCopier.addActionListener(e -> copierMotDePasse());
        panelResultat.add(champMotDePasse, BorderLayout.CENTER);
        panelResultat.add(btnCopier, BorderLayout.EAST);
        main.add(panelResultat);
        main.add(Box.createVerticalStrut(10));

        // Niveau de sécurité
        labelNiveau = new JLabel("Niveau : —");
        labelNiveau.setFont(new Font("SansSerif", Font.BOLD, 13));
        labelNiveau.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(labelNiveau);

        setContentPane(main);
        setPreferredSize(new Dimension(400, 370));
    }

    private JCheckBox creerCheckbox(String texte, boolean selected) {
        JCheckBox cb = new JCheckBox(texte, selected);
        cb.setBackground(new Color(245, 245, 250));
        cb.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return cb;
    }

    private void genererMotDePasse() {
        try {
            int     longueur = (int) spinnerLongueur.getValue();
            boolean maj      = cbMajuscules.isSelected();
            boolean chiff    = cbChiffres.isSelected();
            boolean symb     = cbSymboles.isSelected();
            boolean avance   = cbAvance.isSelected();

            Generateur generateur = avance ? generateurAvance : generateurSimple;
            MotDePasse mp = generateur.generer(longueur, maj, chiff, symb);

            champMotDePasse.setText(mp.getContenu());
            NiveauSecurite niveau = mp.getNiveau();
            labelNiveau.setText("Niveau : " + niveau.getLabel());
            labelNiveau.setForeground(niveau.getCouleur());

        } catch (LongueurInvalideException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void copierMotDePasse() {
        String mdp = champMotDePasse.getText();
        if (mdp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun mot de passe à copier.",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(mdp), null);
        btnCopier.setText("✓ Copié !");
        Timer timer = new Timer(1500, e -> btnCopier.setText("Copier"));
        timer.setRepeats(false);
        timer.start();
    }
}
