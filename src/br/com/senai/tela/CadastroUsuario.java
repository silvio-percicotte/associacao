/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.tela;

import br.com.senai.dao.PerfilDao;
import br.com.senai.dao.PerfilDaoImpl;
import br.com.senai.dao.UsuarioDao;
import br.com.senai.dao.UsuarioDaoImpl;
import br.com.senai.entidade.Perfil;
import br.com.senai.entidade.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author SESI_SENAI
 */
public class CadastroUsuario extends javax.swing.JFrame {

    private Usuario usuario;
    private UsuarioDao usuarioDao = new UsuarioDaoImpl();
    private List<Perfil> perfis;

    public CadastroUsuario() {
        initComponents();
        pesquisarPerfilCombobox();
    }

    public CadastroUsuario(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        varNome.setText(usuario.getNome());
        varLogin.setText(usuario.getLogin());
        pesquisarPerfilCombobox();
    }

    private void pesquisarPerfilCombobox() {
        PerfilDao perfilDao = new PerfilDaoImpl();
        try {
            perfis = perfilDao.pesquisarTodo();
            carregarComboPerfil(perfis);
        } catch (Exception ex) {
            System.err.println("Erro ao carregar combobox " + ex.getMessage());
        }
    }

    private void carregarComboPerfil(List<Perfil> perfis) {
        varComboPerfil.addItem("Selecione um perfil...");
        for (Perfil perfil : perfis) {
            varComboPerfil.addItem(perfil.getNome());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitulo = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        varNome = new javax.swing.JTextField();
        lbNome1 = new javax.swing.JLabel();
        varLogin = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        varComboPerfil = new javax.swing.JComboBox();
        lbNome2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Usuário");

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Cadastro de Usuário");

        lbNome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbNome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNome.setText("Nome:");

        lbNome1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbNome1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNome1.setText("Login:");

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        lbNome2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbNome2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNome2.setText("Perfil:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(varNome))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(varLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(varComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNome)
                    .addComponent(varNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNome1)
                    .addComponent(varLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNome2))
                .addGap(27, 27, 27)
                .addComponent(btSalvar)
                .addGap(0, 69, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        if (usuario == null) {
            usuario = new Usuario();
            usuario.setSenha("12345");
            int linhaSelecionada = varComboPerfil.getSelectedIndex();
            Perfil perfil = perfis.get(--linhaSelecionada);
            usuario.setPerfil(perfil);
        }
        usuario.setNome(varNome.getText().trim());
        usuario.setLogin(varLogin.getText().trim());

        try {
            if (usuario.getId() == null) {
                usuarioDao.salvar(usuario);
                varComboPerfil.removeAllItems();
                carregarComboPerfil(perfis);
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!!");
            } else {
                usuarioDao.alterar(usuario);
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!!");
            }

            limparFormulario();

        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário " + e.getMessage());
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void limparFormulario() {
        varNome.setText(null);
        varLogin.setText(null);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNome1;
    private javax.swing.JLabel lbNome2;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JComboBox varComboPerfil;
    private javax.swing.JTextField varLogin;
    private javax.swing.JTextField varNome;
    // End of variables declaration//GEN-END:variables
}
