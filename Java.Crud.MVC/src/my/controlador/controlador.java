package my.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import my.modelo.Persona;
import my.modelo.PersonaDAO;
import my.vista.vista;

public class controlador implements ActionListener {

    Persona persona = new Persona();
    PersonaDAO personadao = new PersonaDAO();
    vista mivista = new vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public controlador(vista v) {
        this.mivista = v;
        this.mivista.btn_listar.addActionListener(this);
        this.mivista.btn_guardar.addActionListener(this);
        this.mivista.btn_editar.addActionListener(this);
        this.mivista.btn_ok.addActionListener(this); 
        this.mivista.btn_eliminar.addActionListener(this);
        listar(mivista.jTable_tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mivista.btn_listar) {
            limpiarTabla();
            listar(mivista.jTable_tabla);
            
        }
        if (e.getSource() == mivista.btn_guardar) {
            agregar(persona);
            limpiarTabla();
            listar(mivista.jTable_tabla);
        }
        if (e.getSource() == mivista.btn_editar) {
            int fila = mivista.jTable_tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar Una FILA.");
            } else {
                //tomo los datos de la tabla
                int id = Integer.parseInt(mivista.jTable_tabla.getValueAt(fila, 0).toString());
                String nombre = (String) mivista.jTable_tabla.getValueAt(fila, 1);
                String correo = (String) mivista.jTable_tabla.getValueAt(fila, 2);
                int telefono = Integer.parseInt(mivista.jTable_tabla.getValueAt(fila, 3).toString());
                //coloco los datos en los jtextfield
                mivista.jtxt_id.setText("" + id);
                mivista.jtxt_nombre.setText(nombre);
                mivista.jtxt_correo.setText(correo);
                mivista.jtxt_telefono.setText("" + telefono);
                limpiarTabla();
                listar(mivista.jTable_tabla);

            }
            
        }
        if (e.getSource() == mivista.btn_ok) {
            actualizar();
            limpiarTabla();
            listar(mivista.jTable_tabla);
        }
        
        if(e.getSource()== mivista.btn_eliminar){
            eliminar();
            limpiarTabla();
            listar(mivista.jTable_tabla);
        }
    }
    
    public void eliminar(){
        int fila =mivista.jTable_tabla.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(null, "Debe SELECCIONAR un Usuario.");
            }else{
                int id = Integer.parseInt((String)mivista.jTable_tabla.getValueAt(fila, 0).toString());
                personadao.eliminar(id);
                JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO.");
            }
    }

    public void actualizar() {
        int id = Integer.parseInt(mivista.jtxt_id.getText());
        String nombre = mivista.jtxt_nombre.getText().trim();
        String correo = mivista.jtxt_correo.getText().trim();
        int telefono = Integer.parseInt(mivista.jtxt_telefono.getText());

        persona.setId(id);
        persona.setNombre(nombre);
        persona.setCorreo(correo);
        persona.setTelefono(telefono);

        int respuesta = personadao.actualizar(persona);

        if (respuesta == 1) {
            JOptionPane.showMessageDialog(null, "Dato Actualizado con EXITO.");
        } else {
            JOptionPane.showMessageDialog(null, "ERROR, No se pudo ACTUALIZAR.");
        }
    }

    public void agregar(Persona persona) {

        String nombre = mivista.jtxt_nombre.getText().trim();
        String correo = mivista.jtxt_correo.getText().trim();
        int telefono = Integer.parseInt(mivista.jtxt_telefono.getText());

        if (nombre == null || correo == null) {
            throw new IllegalArgumentException("El nombre y el correo no pueden ser nulos.");
        }

        persona.setNombre(nombre);
        persona.setCorreo(correo);
        persona.setTelefono(telefono);

        int respuesta = personadao.agregar(persona);

        if (respuesta == 1) {
            JOptionPane.showMessageDialog(null, "Operación Realizada con EXITO!");
        }
    }

    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();

       // modelo.setRowCount(0); - limpia la tabla y actualiza las columnas y filas

        List<Persona> lista = personadao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getCorreo();
            objeto[3] = lista.get(i).getTelefono();
            modelo.addRow(objeto);
        }
        mivista.jTable_tabla.setModel(modelo);
    }
    
    public void limpiarTabla (){
        for (int i = 0; i < mivista.jTable_tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    }
    
    
}
