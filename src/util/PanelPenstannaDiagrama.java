package util;

import javax.swing.JLabel;

import javax.swing.JPanel;
import Clases.Diagrama;
import Clases.GestorUML;
import Interfaz.Principal;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;

public class PanelPenstannaDiagrama extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Diagrama diagrama;
	private JLabel lblNombreDiagrama;
	private JLabel lblX;
	public PanelPenstannaDiagrama (Diagrama d) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(SystemColor.info);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				colorPanel();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Diagrama diagramaAnterior = GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado();
				GestorUML.getInstancie().getProyectoSeleccionado().setDiagramaSeleccionado(diagrama); // se actualiza el diagrama seleccionado
				Principal.getInstance().actualizarColorPanelPestannaDiagrama(diagramaAnterior); // se actualiza el color del anterior panelPesta�a
				colorPanel(); // se define un color de acuerdo a si est� seleccionado o no el diagrama
				Principal.getInstance().actualizarLienzo(); // se actualiza la informacion del nuevo diagrama en el lienzo
			}
		});
		diagrama = d;
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		addLblNombreDiagrama();
		addLblX();
		colorPanel();

	}

	private void addLblNombreDiagrama () {
		lblNombreDiagrama = new JLabel("");
		lblNombreDiagrama.setText(diagrama.getNombre());
		lblNombreDiagrama.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreDiagrama.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNombreDiagrama);
	}

	public Diagrama getDiagrama() {
		return diagrama;
	}

	public void setDiagrama(Diagrama diagrama) {
		this.diagrama = diagrama;
	}

	private void addLblX () {
		lblX = new JLabel("X");
		lblX.setBorder(null);

		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// HACER COMPROBACION DE GUARDADO O NO
					cerrarDiagrama();		
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(SystemColor.red);
				setBackground(SystemColor.info);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(SystemColor.black);
				colorPanel();
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblX);
	}

	public void colorPanel () {
		if (GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().equals(diagrama))
			setBackground(SystemColor.activeCaption);
		else
			setBackground(SystemColor.inactiveCaptionBorder);
	}

	public void cerrarDiagrama () {
		GestorUML.getInstancie().getProyectoSeleccionado().eliminarSeleccionDiagrama(diagrama);
		Principal.getInstance().actualizarPanelPestannaDiagramas();
		Principal.getInstance().actualizarEstado(); // se actualiza el estado del panel diagrama
	}

}
