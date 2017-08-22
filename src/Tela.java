package eightqueens;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Tela extends Shell {
	private Spinner qtGeracoes;
	private Spinner qtIndIniciais;
	static Label lblResultado;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Tela shell = new Tela(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public Tela(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 374, 248);
		
		Label lblNDeGeraes = new Label(composite, SWT.NONE);
		lblNDeGeraes.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNDeGeraes.setBounds(10, 10, 110, 30);
		lblNDeGeraes.setText("N\u00BA de gera\u00E7\u00F5es:");
		
		Label lblNDeIndivduos = new Label(composite, SWT.NONE);
		lblNDeIndivduos.setText("N\u00BA de indiv\u00EDduos iniciais:");
		lblNDeIndivduos.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNDeIndivduos.setBounds(10, 49, 169, 30);
		
		qtGeracoes = new Spinner(composite, SWT.BORDER);
		qtGeracoes.setSelection(10);
		qtGeracoes.setBounds(182, 10, 41, 22);
		
		qtIndIniciais = new Spinner(composite, SWT.BORDER);
		qtIndIniciais.setSelection(50);
		qtIndIniciais.setBounds(182, 49, 41, 22);
		
		lblResultado = new Label(composite, SWT.BORDER);
		lblResultado.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblResultado.setBounds(10, 85, 356, 153);
		
		Button btnCalcular = new Button(composite, SWT.NONE);
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new Processamento(qtGeracoes.getSelection(), qtIndIniciais.getSelection());
			}
		});
		btnCalcular.setBounds(244, 21, 82, 30);
		btnCalcular.setText("Calcular");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("8 Rainhas");
		setSize(390, 280);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
