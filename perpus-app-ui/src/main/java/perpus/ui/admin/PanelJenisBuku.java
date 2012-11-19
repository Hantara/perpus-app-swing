/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.ui.admin;

import com.jgoodies.looks.HeaderStyle;
import com.jgoodies.looks.Options;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.springframework.util.StringUtils;
import perpus.Main;
import perpus.domain.JenisBuku;
import perpus.ui.TableUtil;
import perpus.ui.tablemodel.JenisBukuTableModel;
import perpus.util.ErrorDialog;

/**
 *
 * @author martinusadyh
 */
public class PanelJenisBuku extends javax.swing.JPanel {
    
    public static final String PANEL_NAME = "Jenis Buku";
    private static PanelJenisBuku panel;
    private List<JenisBuku> jenisBukus = new ArrayList<JenisBuku>();
    private JenisBuku jenisBuku;
    private Integer start = 0;
    private Integer rows = 30;
    
    public static PanelJenisBuku getPanel() {
        if(panel==null){
            panel = new PanelJenisBuku();
        }
        return panel;
    }

    /**
     * Creates new form PanelJenisBuku
     */
    public PanelJenisBuku() {
        initComponents();
        initPaging(null);
        
        loadDataToTable();
        tbl.getSelectionModel().addListSelectionListener(new TableSelection());
    }
    
    private void loadDataToTable() {
        Double hal = (Double) spPaging.getModel().getValue();
        start = (hal.intValue() - 1) * rows;
        
        if(StringUtils.hasText(txtSearch.getText())){
            jenisBukus = Main.getMasterService().findAllJenisBuku(
                    cmbOption.getSelectedItem().toString(), txtSearch.getText(),
                    start, rows);
        } else {
            jenisBukus = Main.getMasterService().findAllJenisBuku(null, null, start, rows);
        }
        
        if (!jenisBukus.isEmpty()) {
            tbl.setModel(new JenisBukuTableModel(jenisBukus));
            TableUtil.initColumn(tbl);
        } else {
            tbl.setModel(new JenisBukuTableModel(new ArrayList<JenisBuku>()));
            TableUtil.initColumn(tbl);
        }
    }
    
    private void initPaging(Double selectedPage){
        //start konfigurasi untuk paging
        Long count = 0L;
        
        if(StringUtils.hasText(txtSearch.getText())) {
            count = Main.getMasterService().countAllJenisBuku(
                    cmbOption.getSelectedItem().toString(), txtSearch.getText());
        } else {
            count = Main.getMasterService().countAllJenisBuku();
        }
        
        Long hasilBagi = count/rows;
        long page = Math.round(hasilBagi); 
        
        if ((count%rows) > 0) {
            page = page + 1;
        }
        if(page==0){page=1;}
        
        lblMaxPage.setText(String.valueOf(page));
        spPaging.setModel(new SpinnerNumberModel(1, 1, page, 1));
        lblCountRows.setText(String.valueOf(count));
        if(selectedPage != null){
            spPaging.getModel().setValue(selectedPage);
        }
        //end konfigurasi untuk paging
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnRefresh = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        lblRows = new javax.swing.JLabel();
        lblCountRows = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMinPage = new javax.swing.JLabel();
        spPaging = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        lblMaxPage = new javax.swing.JLabel();
        cmbOption = new javax.swing.JComboBox();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.putClientProperty(Options.HEADER_STYLE_KEY, HeaderStyle.BOTH);

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/refresh.gif"))); // NOI18N
        btnRefresh.setToolTipText("Refresh");
        btnRefresh.setFocusable(false);
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRefresh);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/add.gif"))); // NOI18N
        btnAdd.setToolTipText("Tambah Data");
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAdd);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/edit.gif"))); // NOI18N
        btnEdit.setToolTipText("Edit Data");
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEdit);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/delete.gif"))); // NOI18N
        btnDelete.setToolTipText("Hapus Data");
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDelete);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/img/close.gif"))); // NOI18N
        btnClose.setToolTipText("Tutup Form");
        btnClose.setFocusable(false);
        btnClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClose.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jToolBar1.add(btnClose);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.putClientProperty(Options.HEADER_STYLE_KEY, HeaderStyle.BOTH);

        lblRows.setText(" rows : ");
        lblRows.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblRows.setMaximumSize(new java.awt.Dimension(55, 55));
        lblRows.setMinimumSize(new java.awt.Dimension(10, 10));
        jToolBar2.add(lblRows);

        lblCountRows.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblCountRows.setMaximumSize(new java.awt.Dimension(50, 50));
        lblCountRows.setMinimumSize(new java.awt.Dimension(10, 10));
        jToolBar2.add(lblCountRows);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Page");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel3.setMaximumSize(new java.awt.Dimension(50, 50));
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 50));
        jToolBar2.add(jLabel3);

        lblMinPage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMinPage.setText("  1  ");
        lblMinPage.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblMinPage.setMaximumSize(new java.awt.Dimension(30, 30));
        lblMinPage.setMinimumSize(new java.awt.Dimension(10, 10));
        jToolBar2.add(lblMinPage);

        spPaging.setMaximumSize(new java.awt.Dimension(50, 50));
        spPaging.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spPagingStateChanged(evt);
            }
        });
        jToolBar2.add(spPaging);

        jLabel4.setMaximumSize(new java.awt.Dimension(10, 10));
        jToolBar2.add(jLabel4);

        lblMaxPage.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblMaxPage.setMaximumSize(new java.awt.Dimension(50, 50));
        lblMaxPage.setMinimumSize(new java.awt.Dimension(10, 10));
        jToolBar2.add(lblMaxPage);

        cmbOption.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "KODE", "JENIS" }));
        cmbOption.setMaximumSize(new java.awt.Dimension(140, 140));
        jToolBar2.add(cmbOption);

        txtSearch.setMaximumSize(new java.awt.Dimension(150, 150));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jToolBar2.add(txtSearch);

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Jenis"
            }
        ));
        tbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            initPaging((Double) spPaging.getModel().getValue());
            loadDataToTable();
        } catch (Exception e) {
            ErrorDialog.showErrorDialog(e);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            jenisBuku = new FormDialogJenisBuku().showDialog();
            if (jenisBuku != null) {
                Main.getMasterService().save(jenisBuku);
                loadDataToTable();
            }
        } catch (Exception e) {
            ErrorDialog.showErrorDialog(e);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            if (tbl.getSelectedRow() >= 0 && jenisBuku != null) {
                jenisBuku = new FormDialogJenisBuku().editDialog(jenisBuku);
                if (jenisBuku != null) {
                    Main.getMasterService().save(jenisBuku);
                    loadDataToTable();
                }
            } else {
                JOptionPane.showMessageDialog(Main.getMainForm(),
                    "Tidak ada data yang ingin di edit !!",
                    "Terjadi Kesalahan !!",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            ErrorDialog.showErrorDialog(e);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            if (tbl.getSelectedRow() >= 0 && jenisBuku != null) {
                Main.getMasterService().delete(jenisBuku);
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(Main.getMainForm(),
                    "Tidak ada data yang ingin di hapus !!",
                    "Terjadi Kesalahan !!",
                    JOptionPane.ERROR_MESSAGE);
            }
            initPaging((Double) spPaging.getModel().getValue());
        } catch (Exception e) {
            ErrorDialog.showErrorDialog(e);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        Main.getMainForm().getMainTabbedPane().remove(this);
        panel = null;
    }//GEN-LAST:event_btnCloseActionPerformed

    private void spPagingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spPagingStateChanged
        loadDataToTable();
    }//GEN-LAST:event_spPagingStateChanged

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            initPaging(null);
            loadDataToTable();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private class TableSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            if (tbl.getSelectedRow() >= 0) {
                jenisBuku = jenisBukus.get(tbl.getSelectedRow());
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox cmbOption;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblCountRows;
    private javax.swing.JLabel lblMaxPage;
    private javax.swing.JLabel lblMinPage;
    private javax.swing.JLabel lblRows;
    private javax.swing.JSpinner spPaging;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
