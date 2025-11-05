package AppPkg;

import javax.swing.JOptionPane;

public class MainMenu extends javax.swing.JFrame
{

    public MainMenu()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblGreeting1 = new javax.swing.JLabel();
        lblGreeting2 = new javax.swing.JLabel();
        lblQuestion = new javax.swing.JLabel();
        txfAnimal = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu");

        lblGreeting1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblGreeting1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGreeting1.setText("Hello User");

        lblGreeting2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGreeting2.setText("Welcome to SKALBS'S Animal Encyclopdia");

        lblQuestion.setText("What animal are you searching for?");

        btnFilter.setText("jButton1");
        btnFilter.setPreferredSize(new java.awt.Dimension(393, 369));

        btnSearch.setText("Search");

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/settings.png"))); // NOI18N
        btnSettings.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGreeting1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblGreeting2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSettings))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(btnSearch))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(lblQuestion)
                                .addGap(43, 43, 43)
                                .addComponent(txfAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSettings)
                .addGap(2, 2, 2)
                .addComponent(lblGreeting1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGreeting2)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuestion)
                    .addComponent(txfAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSettingsActionPerformed
    {//GEN-HEADEREND:event_btnSettingsActionPerformed
        new Settings().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSettingsActionPerformed

    public static void main(String args[])
    {
        new MainMenu().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSettings;
    private javax.swing.JLabel lblGreeting1;
    private javax.swing.JLabel lblGreeting2;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JTextField txfAnimal;
    // End of variables declaration//GEN-END:variables
}
