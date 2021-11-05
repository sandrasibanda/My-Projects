/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fa1praxis2021;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFormattedTextField;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import net.proteanit.sql.DbUtils;
/**
 *
 * @author HARDLEY PHETLO
 */
public  class frmStaff extends javax.swing.JFrame {

    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    /**
     * Creates new form frmStaff
     */
    public frmStaff() {
        initComponents();
        hideReports();
    }

    private  void hideReports()
    { 
        panelPROJECTS.setVisible(false);
         panelVOLUNTEERSREPORT.setVisible(false);
         panelAPPLICANTSREPORT.setVisible(true);
         panelUSERSREPORT.setVisible(false);
    }
    public void AddPROJECTS()
    {
    try{
            String query="INSERT INTO `tbl_projects1` (`id`, `project_type`, `materials`, `applicant_name`) VALUES (NULL, ?, ?, ?)";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1,String.valueOf(cboProjectType.getSelectedItem()));
            pst.setString(2, String.valueOf(cboProjectMat.getSelectedItem()));
            pst.setString(3, txtApplicant.getText());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Project Activated and Saved!");
            
            txtApplicant.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void AddApplicant()
    {
        
    try{
            String query="INSERT INTO `db_fa1`.`tbl_applicants1` (`applicant_name`, `service`, `applicant_address`, `applicant_contact`,`applicant_created_on`, `Status`) VALUES (?, ?,?,?, current_timestamp(), 'APPROVED')";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            
            pst.setString(1, txtA_NAME.getText());
            pst.setString(2,String.valueOf(cboSERVICE.getSelectedItem()));
            pst.setString(3, txtAddress.getText());
            pst.setString(4, txtContact.getText());
            
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Applicant Accepted and Saved!");
            txtA_NAME.setText("");
            txtAddress.setText("");
            txtContact.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    try
        {
            txtContact = new JFormattedTextField(new MaskFormatter("##########"));
        }catch(java.text.ParseException e)
        {
            JOptionPane.showMessageDialog(null,"Enter Format: 0123456789"+ e);
        }
    }
     public void AddVolunteer()
    {
        String Vlogin="Volunteer";
    try{
            String query="INSERT INTO `tbl_staff1` (`id`, `v_name`, `v_service`, `v_duration`, `v_created_on`) VALUES (NULL, ?, ?, ?, current_timestamp())";
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1,txtProjectName1.getText());
            pst.setString(2, String.valueOf(jComboBox2.getSelectedItem()));
            pst.setString(3,String.valueOf(jComboBox3.getSelectedItem()));
             pst.executeUpdate();
            String query1="INSERT INTO `tbl_login1` (`username`, `password`, `usertype`) VALUES (?,?,?)";
            pst=conn.prepareStatement(query1);
            pst.setString(1,txtVusername.getText());
            pst.setString(2, txtVpass.getText());
            pst.setString(3, Vlogin);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Volunteer Registerd Sucessfully!");
            txtProjectName1.setText("");
           
            txtVusername.setText("");
            txtVpass.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
      public void AddUserLogin()
    {
        
    try{
            String query="INSERT INTO `tbl_login1` (`username`, `password`, `usertype`) VALUES (?,?,?)";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1,txtUSERNAME.getText());
            pst.setString(2, txtPASSWORD.getText());
            pst.setString(3, String.valueOf(jcboUtype.getSelectedItem()));
           
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Login User Registerd Sucessfully!");
            txtUSERNAME.setText("");
            txtPASSWORD.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
   
   public void updateUserLogin()
    {
        
    try{
       


            String query="UPDATE `db_fa1`.`tbl_login1` SET `username` = '"+txtUSERNAME.getText()+"', `password` = '"+txtPASSWORD.getText()+"', `usertype` = '"+txtUSERTYPE.getText()+"' WHERE (`username` = '"+txtUSERNAME.getText()+"')";            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Login User Updated Sucessfully!");
            txtUSERNAME.setText("");
            txtPASSWORD.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
     public void searchV()
     {
          try
        {
            
            
             String query="SELECT * FROM `tbl_staff1` WHERE v_name=?";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1, txtSearch.getText()); 

            rs=pst.executeQuery();
            if(rs.next()==true)
            {
                String vID=rs.getString(1);
                String vName=rs.getString(2);
                String vSERVICE=rs.getString(3);
                String vDuration=rs.getString(4);
                String vCREATEDON=rs.getString(5);
                
                txtProjectName4.setText(vName);
                txtProjectName5.setText(vSERVICE);
                txtProjectName7.setText(vDuration);
                txtProjectName8.setText(vCREATEDON);
                
                
                JOptionPane.showMessageDialog(this,"search complete!");
                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"NO records found!");
                txtProjectName4.setText("");
                txtProjectName5.setText("");
                txtProjectName7.setText("");
                
            }
            
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
     }
     
     public void searchU()
     {
          try
        {
            
            
             String query="SELECT * FROM `tbl_login1` WHERE username=?";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1, txtSearchLOGIN.getText()); 

            rs=pst.executeQuery();
            if(rs.next()==true)
            {
                String UNAME=rs.getString(1);
                String UPASS=rs.getString(2);
                String UTYPE=rs.getString(3);
                
                
                txtUSERNAME.setText(UNAME);
                txtPASSWORD.setText(UPASS);
                txtUSERTYPE.setText(UTYPE);
                
                
                
                JOptionPane.showMessageDialog(this,"search complete!");
                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"NO records found!");
                txtUSERNAME.setText("");
                txtPASSWORD.setText("");
                txtUSERTYPE.setText("");
                
            }
            
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
     }
     public void searchA()
     {
          try
        {
            
            
             String query="SELECT * FROM `tbl_applicants1` WHERE applicant_name=?";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1, txtSearchA.getText()); 

            rs=pst.executeQuery();
            if(rs.next()==true)
            {
                String ID=rs.getString(1);
                String ANAME=rs.getString(2);
                String ASERVICE=rs.getString(3);
                String AADDRESS=rs.getString(4);
                String ACONTACT=rs.getString(5);
                
               txtID.setText(ID);
                txtA_NAME.setText(ANAME);
                txtServiceA.setText(ASERVICE);
                txtAddress.setText(AADDRESS);
                txtContact.setText(ACONTACT);
                
                
                
                JOptionPane.showMessageDialog(this,"search complete!");
                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"NO records found!");
                txtUSERNAME.setText("");
                txtPASSWORD.setText("");
                txtUSERTYPE.setText("");
                
            }
            
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
     }
    public void loadPROJECTS()
    {
        panelPROJECTS.setVisible(true);
         panelVOLUNTEERSREPORT.setVisible(false);
         panelAPPLICANTSREPORT.setVisible(false);
         panelUSERSREPORT.setVisible(false);
        
         try
        {
             String query="SELECT * FROM `tbl_projects1`";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
 
            rs=pst.executeQuery();
            jtabPROJECTS.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void loadStaff()
    {
        panelPROJECTS.setVisible(false);
         panelVOLUNTEERSREPORT.setVisible(true);
         panelAPPLICANTSREPORT.setVisible(false);
         panelUSERSREPORT.setVisible(false);
         try
        {
             String query="SELECT * FROM `tbl_staff1`";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
 
            rs=pst.executeQuery();
            jtabStaff.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void loadApplicants()
    {
         
        panelPROJECTS.setVisible(false);
         panelVOLUNTEERSREPORT.setVisible(false);
         panelAPPLICANTSREPORT.setVisible(true);
         panelUSERSREPORT.setVisible(false);
         try
        {
             String query="SELECT * FROM `tbl_applicants1`";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
 
            rs=pst.executeQuery();
            jtabAPPLICANTS.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void loadUSERS()
    {
         
        panelPROJECTS.setVisible(false);
         panelVOLUNTEERSREPORT.setVisible(false);
         panelAPPLICANTSREPORT.setVisible(false);
         panelUSERSREPORT.setVisible(true);
         try
        {
             String query="SELECT * FROM `tbl_login1`";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
 
            rs=pst.executeQuery();
            jtabLOGIN.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void deleteUserLogin()
    {
        
    try{

            String query="DELETE FROM `db_fa1`.`tbl_login1` WHERE username =?";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1,txtUSERNAME.getText());
           
           
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Login User Deleted Sucessfully!");
            txtUSERNAME.setText("");
            txtPASSWORD.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void deleteStaff()
    {
        
    try{
            String query="DELETE * FROM `tbl_login1` WHERE v_name = ? ";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1,txtSearch.getText());
           
           
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Volunteer  Deleted Sucessfully!");
            txtUSERNAME.setText("");
            txtPASSWORD.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void deleteApplicant()
    {
        
    try{
       

            String query=" DELETE FROM `db_fa1`.`tbl_applicants1` WHERE id = ? ";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1,txtID.getText());
           
           
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Applicant  Deleted Sucessfully!");
            txtUSERNAME.setText("");
            txtPASSWORD.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
   // 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnAddProject = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtApplicant = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboProjectType = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cboProjectMat = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        btnRegisterV = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtProjectName1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtVName = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtVusername = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtVpass = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnProjectsReports = new javax.swing.JButton();
        btnVolunteeRrpt = new javax.swing.JButton();
        btnApplicationReport = new javax.swing.JButton();
        jLayerREPORTS = new javax.swing.JLayeredPane();
        panelAPPLICANTSREPORT = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtabAPPLICANTS = new javax.swing.JTable();
        panelPROJECTS = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabPROJECTS = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        panelVOLUNTEERSREPORT = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtabStaff = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        panelUSERSREPORT = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtabLOGIN = new javax.swing.JTable();
        btnLOGINReports = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnRejectApplicant = new javax.swing.JButton();
        txtA_NAME = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboSERVICE = new javax.swing.JComboBox<>();
        btnSaveApplicant1 = new javax.swing.JButton();
        txtContact = new javax.swing.JTextField();
        lblContactCheck = new javax.swing.JLabel();
        btnSEARCHAPLICANT = new javax.swing.JButton();
        btnDeleteApplicant = new javax.swing.JButton();
        txtSearchA = new javax.swing.JTextField();
        txtServiceA = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUodate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        txtSearchLOGIN = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtUSERNAME = new javax.swing.JTextField();
        txtPASSWORD = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtUSERTYPE = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jcboUtype = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSaveApplicant2 = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtProjectName4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtProjectName5 = new javax.swing.JTextField();
        btnSEARCH = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtProjectName7 = new javax.swing.JTextField();
        btnDeleteV = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtProjectName8 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 255, 0));

        jPanel6.setBackground(new java.awt.Color(255, 204, 51));

        btnAddProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fa1praxis2021/icons/Statistics_48px.png"))); // NOI18N
        btnAddProject.setText("ADD NEW PROJECT");
        btnAddProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProjectActionPerformed(evt);
            }
        });

        jLabel8.setText("Material Needed:");

        jLabel9.setText("HomeOwner:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setText("PROJECT DETAILS");

        cboProjectType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plumbing", "Broken furniture", "Collapsing ceilings", "Electrical faults", "Gardening" }));
        cboProjectType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProjectTypeActionPerformed(evt);
            }
        });

        jLabel13.setText("PROJECT_TYPE");

        cboProjectMat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plumbing kit", "Broken furniture kit", "Collapsing ceilings kit", "Electrical faults kit", "Gardening kit" }));
        cboProjectMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProjectMatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddProject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel10))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtApplicant)
                                    .addComponent(cboProjectType, 0, 207, Short.MAX_VALUE)
                                    .addComponent(cboProjectMat, 0, 207, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(418, 418, 418))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboProjectType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboProjectMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApplicant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddProject)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 204, 51));

        btnRegisterV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fa1praxis2021/icons/Add User Male_48px.png"))); // NOI18N
        btnRegisterV.setText("REGISTER");
        btnRegisterV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterVActionPerformed(evt);
            }
        });

        jLabel11.setText("VOLUNTEER NAME:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel14.setText("VOLUNTEER DETAILS");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plumbing", "Broken furniture", "Collapsing ceilings", "Electrical faults", "Gardening" }));

        jLabel12.setText("ASSIGN TO:");

        jLabel18.setText("DURATION: ");

        jLabel29.setText("USERNAME:");

        jLabel30.setText("PASSWORD:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2 WEEKS", "3 WEEKS", "5 WEEKS", "2 MONTHS", "6 MONTHS" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegisterV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtVusername, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVpass, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(jLabel14))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addGap(18, 18, 18))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(59, 59, 59)))
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(txtProjectName1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtVName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProjectName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtVName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnRegisterV)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Projects", jPanel2);

        jPanel5.setBackground(new java.awt.Color(102, 51, 255));

        btnProjectsReports.setText("VIEW PROJECTS REPORT");
        btnProjectsReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProjectsReportsActionPerformed(evt);
            }
        });

        btnVolunteeRrpt.setText("VIEW VOLUNTEERS REPORT");
        btnVolunteeRrpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolunteeRrptActionPerformed(evt);
            }
        });

        btnApplicationReport.setText("VIEW APPLICATIONS REPORT");
        btnApplicationReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplicationReportActionPerformed(evt);
            }
        });

        jLayerREPORTS.setBackground(new java.awt.Color(153, 153, 0));

        panelAPPLICANTSREPORT.setBackground(new java.awt.Color(102, 51, 255));
        panelAPPLICANTSREPORT.setPreferredSize(new java.awt.Dimension(757, 402));

        jLabel26.setFont(new java.awt.Font("Wide Latin", 1, 24)); // NOI18N
        jLabel26.setText("APPLICANT REPORT");

        jtabAPPLICANTS.setBackground(new java.awt.Color(51, 255, 51));
        jtabAPPLICANTS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "NAME", "SERVICE", "ADDRESS", "CONTACT", "CREATED_ON"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtabAPPLICANTS);

        javax.swing.GroupLayout panelAPPLICANTSREPORTLayout = new javax.swing.GroupLayout(panelAPPLICANTSREPORT);
        panelAPPLICANTSREPORT.setLayout(panelAPPLICANTSREPORTLayout);
        panelAPPLICANTSREPORTLayout.setHorizontalGroup(
            panelAPPLICANTSREPORTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAPPLICANTSREPORTLayout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(123, 123, 123))
        );
        panelAPPLICANTSREPORTLayout.setVerticalGroup(
            panelAPPLICANTSREPORTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAPPLICANTSREPORTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelPROJECTS.setBackground(new java.awt.Color(102, 51, 255));
        panelPROJECTS.setPreferredSize(new java.awt.Dimension(757, 402));

        jtabPROJECTS.setBackground(new java.awt.Color(51, 255, 51));
        jtabPROJECTS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "PROJECT_TYPE", "MATERIALS", "APPLICANT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtabPROJECTS);

        jLabel24.setFont(new java.awt.Font("Wide Latin", 1, 24)); // NOI18N
        jLabel24.setText("PROJECT REPORT");

        javax.swing.GroupLayout panelPROJECTSLayout = new javax.swing.GroupLayout(panelPROJECTS);
        panelPROJECTS.setLayout(panelPROJECTSLayout);
        panelPROJECTSLayout.setHorizontalGroup(
            panelPROJECTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPROJECTSLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel24)
                .addGap(0, 163, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelPROJECTSLayout.setVerticalGroup(
            panelPROJECTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPROJECTSLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
        );

        panelVOLUNTEERSREPORT.setBackground(new java.awt.Color(102, 51, 255));
        panelVOLUNTEERSREPORT.setPreferredSize(new java.awt.Dimension(757, 402));

        jtabStaff.setBackground(new java.awt.Color(51, 255, 51));
        jtabStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "NAME", "SERVICE", "DURATION", "CREATED_ON"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtabStaff);

        jLabel25.setFont(new java.awt.Font("Wide Latin", 1, 24)); // NOI18N
        jLabel25.setText("VOLUNTEER REPORT");

        javax.swing.GroupLayout panelVOLUNTEERSREPORTLayout = new javax.swing.GroupLayout(panelVOLUNTEERSREPORT);
        panelVOLUNTEERSREPORT.setLayout(panelVOLUNTEERSREPORTLayout);
        panelVOLUNTEERSREPORTLayout.setHorizontalGroup(
            panelVOLUNTEERSREPORTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVOLUNTEERSREPORTLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel25)
                .addContainerGap(98, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelVOLUNTEERSREPORTLayout.setVerticalGroup(
            panelVOLUNTEERSREPORTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVOLUNTEERSREPORTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        panelUSERSREPORT.setBackground(new java.awt.Color(102, 51, 255));
        panelUSERSREPORT.setPreferredSize(new java.awt.Dimension(757, 402));

        jLabel28.setFont(new java.awt.Font("Wide Latin", 1, 24)); // NOI18N
        jLabel28.setText("USERS LOGIN REPORT");

        jtabLOGIN.setBackground(new java.awt.Color(0, 255, 0));
        jtabLOGIN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "USERNAME", "PASSWORD", "USERTYPE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabLOGIN.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jtabLOGIN);

        javax.swing.GroupLayout panelUSERSREPORTLayout = new javax.swing.GroupLayout(panelUSERSREPORT);
        panelUSERSREPORT.setLayout(panelUSERSREPORTLayout);
        panelUSERSREPORTLayout.setHorizontalGroup(
            panelUSERSREPORTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUSERSREPORTLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jLabel28)
                .addContainerGap(97, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelUSERSREPORTLayout.setVerticalGroup(
            panelUSERSREPORTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUSERSREPORTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayerREPORTS.setLayer(panelAPPLICANTSREPORT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerREPORTS.setLayer(panelPROJECTS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerREPORTS.setLayer(panelVOLUNTEERSREPORT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayerREPORTS.setLayer(panelUSERSREPORT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayerREPORTSLayout = new javax.swing.GroupLayout(jLayerREPORTS);
        jLayerREPORTS.setLayout(jLayerREPORTSLayout);
        jLayerREPORTSLayout.setHorizontalGroup(
            jLayerREPORTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAPPLICANTSREPORT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
            .addGroup(jLayerREPORTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayerREPORTSLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPROJECTS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jLayerREPORTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayerREPORTSLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelVOLUNTEERSREPORT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jLayerREPORTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayerREPORTSLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelUSERSREPORT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jLayerREPORTSLayout.setVerticalGroup(
            jLayerREPORTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAPPLICANTSREPORT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayerREPORTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPROJECTS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayerREPORTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelVOLUNTEERSREPORT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayerREPORTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayerREPORTSLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelUSERSREPORT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnLOGINReports.setText("VIEW LOGINS REPORT");
        btnLOGINReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLOGINReportsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnApplicationReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVolunteeRrpt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLOGINReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProjectsReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayerREPORTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayerREPORTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnApplicationReport, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVolunteeRrpt, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProjectsReports, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnLOGINReports, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reports", jPanel5);

        btnRejectApplicant.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRejectApplicant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fa1praxis2021/icons/icons8-cancel-48.png"))); // NOI18N
        btnRejectApplicant.setText("REJECT");
        btnRejectApplicant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectApplicantActionPerformed(evt);
            }
        });

        jLabel2.setText("NAME");

        jLabel3.setText("SERVICE");

        jLabel4.setText("ADDRESS");

        jLabel5.setText("CONTACT");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setText("DETAILS FOR WORK ORDER");

        cboSERVICE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plumbing", "Broken furniture", "Collapsing ceilings", "Electrical faults", "Gardening" }));

        btnSaveApplicant1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSaveApplicant1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fa1praxis2021/icons/Add User Male_48px.png"))); // NOI18N
        btnSaveApplicant1.setText("ACCEPT");
        btnSaveApplicant1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveApplicant1ActionPerformed(evt);
            }
        });

        txtContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactKeyPressed(evt);
            }
        });

        lblContactCheck.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnSEARCHAPLICANT.setText("SEARCH WORK ORDER BY NAME");
        btnSEARCHAPLICANT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSEARCHAPLICANTActionPerformed(evt);
            }
        });

        btnDeleteApplicant.setText("DELETE WORK ORDER BY NAME");
        btnDeleteApplicant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteApplicantActionPerformed(evt);
            }
        });

        jLabel31.setText("ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(btnSaveApplicant1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRejectApplicant, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(350, 350, 350)
                                .addComponent(cboSERVICE, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtServiceA, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(359, 359, 359)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 76, 76))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtA_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtContact)
                                                .addComponent(lblContactCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(197, 197, 197)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSEARCHAPLICANT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteApplicant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearchA, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtA_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSERVICE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtServiceA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtSearchA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSEARCHAPLICANT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteApplicant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblContactCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRejectApplicant, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSaveApplicant1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Applicant", jPanel1);

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUodate.setText("UPDATE");
        btnUodate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUodateActionPerformed(evt);
            }
        });

        btnNew.setText("REGISTER");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel7.setText("SEARCH BY USERNAME:");

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel20.setText("LOGIN SETTINGS  ");

        jLabel21.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel21.setText("USERNAME");

        txtUSERNAME.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txtUSERNAME.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPASSWORD.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txtPASSWORD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel22.setText("PASSWORD");

        txtUSERTYPE.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txtUSERTYPE.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel23.setText("USERTYPE");

        jcboUtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Staff", "Applicant", "Volunteer" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jLabel21))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUSERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUSERTYPE, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchLOGIN, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcboUtype, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnUodate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchLOGIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUodate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUSERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUSERTYPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcboUtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("LOGIN SETTINGS", jPanel8);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 52)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WELCOME TO STAR AGENCY");

        btnSaveApplicant2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSaveApplicant2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fa1praxis2021/icons/icons8-refresh-48.png"))); // NOI18N
        btnSaveApplicant2.setText("RELOAD");
        btnSaveApplicant2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveApplicant2ActionPerformed(evt);
            }
        });

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fa1praxis2021/icons/Shutdown_48px.png"))); // NOI18N
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSaveApplicant2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveApplicant2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setText("VOLUNTEER NAME:");

        txtProjectName4.setEditable(false);

        jLabel17.setText("PROJECT");

        txtProjectName5.setEditable(false);

        btnSEARCH.setText("SEARCH");
        btnSEARCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSEARCHActionPerformed(evt);
            }
        });

        jLabel15.setText("SEARCH BY VOLUNTEER NAME: ");

        jLabel19.setText("DURATION");

        txtProjectName7.setEditable(false);

        btnDeleteV.setText("DELETE");
        btnDeleteV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteVActionPerformed(evt);
            }
        });

        jLabel27.setText("CREATED_ON:");

        txtProjectName8.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(btnSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(txtProjectName4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtProjectName5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(txtProjectName7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(txtProjectName8, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnDeleteV, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSEARCH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProjectName8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProjectName4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProjectName5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProjectName7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        btnNew.enable(false);
        
        searchU();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSaveApplicant2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveApplicant2ActionPerformed
        // TODO add your handling code here:
        frmStaff s = new frmStaff();
        s.show();
        this.hide();
    }//GEN-LAST:event_btnSaveApplicant2ActionPerformed

    private void btnSEARCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSEARCHActionPerformed
        // TODO add your handling code here:
        searchV();
    }//GEN-LAST:event_btnSEARCHActionPerformed

    private void btnRegisterVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterVActionPerformed
        // TODO add your handling code here:
        AddVolunteer();
    }//GEN-LAST:event_btnRegisterVActionPerformed

    private void btnAddProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProjectActionPerformed
        // TODO add your handling code here:
        AddPROJECTS();
    }//GEN-LAST:event_btnAddProjectActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        txtUSERTYPE.enable(false);
        AddUserLogin();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUodateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUodateActionPerformed
        // TODO add your handling code here:
        txtUSERTYPE.enable(false);
        updateUserLogin();
    }//GEN-LAST:event_btnUodateActionPerformed

    private void btnSaveApplicant1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveApplicant1ActionPerformed
        // TODO add your handling code here:
        AddApplicant();
        
    }//GEN-LAST:event_btnSaveApplicant1ActionPerformed

    private void btnRejectApplicantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectApplicantActionPerformed
        // TODO add your handling code here:
            
    try{
        String status="REJECTED!";
            String query="INSERT INTO `tbl_applicants1` (`id`, `applicant_name`, `service`, `applicant_address`, `applicant_contact`, `applicant_created_on``, `Status`) VALUES (NULL, ?, ?, ?, ?, current_timestamp(),?)";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            
            pst.setString(1, txtA_NAME.getText());
            pst.setString(2,String.valueOf(cboSERVICE.getSelectedItem()));
            pst.setString(3, txtAddress.getText());
            pst.setString(4, txtContact.getText());
            pst.setString(5, status);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Applicant Rejected!!!!");
            txtA_NAME.setText("");
            txtAddress.setText("");
            txtContact.setText("");
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    try
        {
            txtContact = new JFormattedTextField(new MaskFormatter("##########"));
        }catch(java.text.ParseException e)
        {
            JOptionPane.showMessageDialog(null,"Enter Format: 0123456789"+ e);
        }
        
    }//GEN-LAST:event_btnRejectApplicantActionPerformed

    private void btnApplicationReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplicationReportActionPerformed
        // TODO add your handling code here:
        loadApplicants();
    }//GEN-LAST:event_btnApplicationReportActionPerformed

    private void btnVolunteeRrptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolunteeRrptActionPerformed
        // TODO add your handling code here:
        loadStaff();
    }//GEN-LAST:event_btnVolunteeRrptActionPerformed

    private void btnProjectsReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjectsReportsActionPerformed
        // TODO add your handling code here:
        loadPROJECTS();
    }//GEN-LAST:event_btnProjectsReportsActionPerformed

    private void btnLOGINReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLOGINReportsActionPerformed
        // TODO add your handling code here:
        loadUSERS();
    }//GEN-LAST:event_btnLOGINReportsActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deleteUserLogin();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        frmLogin L = new frmLogin();
        L.show();
        this.hide();
    }//GEN-LAST:event_btnLogoutActionPerformed

   
    private void txtContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactKeyPressed
        // TODO add your handling code here:
        //getting only numbers
           String value = txtContact.getText();
            int l = value.length();
     
            if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
               txtContact.setEditable(true);
               lblContactCheck.setText("");
            } else {
               txtContact.setEditable(false);
               lblContactCheck.setText("* Enter only numeric digits(0-9)");
            }
           
            if (l == 10) {
                evt.consume();
            } else if (l > 10) {
                lblContactCheck.setText("ENTER ONLY 10 DIGITS");
                txtContact.setText("");
                txtContact.enable(true);
            }
        
    }//GEN-LAST:event_txtContactKeyPressed

    private void btnDeleteVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteVActionPerformed
        // TODO add your handling code here:
        deleteStaff();
    }//GEN-LAST:event_btnDeleteVActionPerformed

    private void btnSEARCHAPLICANTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSEARCHAPLICANTActionPerformed
        // TODO add your handling code here:
        searchA();
    }//GEN-LAST:event_btnSEARCHAPLICANTActionPerformed

    private void btnDeleteApplicantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteApplicantActionPerformed
        // TODO add your handling code here:
        deleteApplicant();
    }//GEN-LAST:event_btnDeleteApplicantActionPerformed

    private void cboProjectTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProjectTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProjectTypeActionPerformed

    private void cboProjectMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProjectMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProjectMatActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(frmStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmStaff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProject;
    private javax.swing.JButton btnApplicationReport;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteApplicant;
    private javax.swing.JButton btnDeleteV;
    private javax.swing.JButton btnLOGINReports;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnProjectsReports;
    private javax.swing.JButton btnRegisterV;
    private javax.swing.JButton btnRejectApplicant;
    private javax.swing.JButton btnSEARCH;
    private javax.swing.JButton btnSEARCHAPLICANT;
    private javax.swing.JButton btnSaveApplicant1;
    private javax.swing.JButton btnSaveApplicant2;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUodate;
    private javax.swing.JButton btnVolunteeRrpt;
    private javax.swing.JComboBox<String> cboProjectMat;
    private javax.swing.JComboBox<String> cboProjectType;
    private javax.swing.JComboBox<String> cboSERVICE;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayerREPORTS;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcboUtype;
    private javax.swing.JTable jtabAPPLICANTS;
    private javax.swing.JTable jtabLOGIN;
    private javax.swing.JTable jtabPROJECTS;
    private javax.swing.JTable jtabStaff;
    private javax.swing.JLabel lblContactCheck;
    private javax.swing.JPanel panelAPPLICANTSREPORT;
    private javax.swing.JPanel panelPROJECTS;
    private javax.swing.JPanel panelUSERSREPORT;
    private javax.swing.JPanel panelVOLUNTEERSREPORT;
    private javax.swing.JTextField txtA_NAME;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtApplicant;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtPASSWORD;
    private javax.swing.JTextField txtProjectName1;
    private javax.swing.JTextField txtProjectName4;
    private javax.swing.JTextField txtProjectName5;
    private javax.swing.JTextField txtProjectName7;
    private javax.swing.JTextField txtProjectName8;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchA;
    private javax.swing.JTextField txtSearchLOGIN;
    private javax.swing.JTextField txtServiceA;
    private javax.swing.JTextField txtUSERNAME;
    private javax.swing.JTextField txtUSERTYPE;
    private javax.swing.JTextField txtVName;
    private javax.swing.JTextField txtVpass;
    private javax.swing.JTextField txtVusername;
    // End of variables declaration//GEN-END:variables
}
