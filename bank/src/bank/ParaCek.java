package bank;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ParaCek extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
     * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
                    try {
                        ParaCek frame = new ParaCek();
                        frame.setVisible(true);
                    } catch (Exception e) {
                    }
                });
	}

	/**
	 * Create the frame.
	 */
	
	private static final String JSON_FILE_PATH = "C:\\Users\\berka\\OneDrive\\Masaüstü\\bank\\Bank_Management\\src\\veri.json";
	
	
	
	public ParaCek() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_mesaj = new JLabel("");
		lbl_mesaj.setFont(new Font("SansSerif", Font.BOLD, 13));
		lbl_mesaj.setBounds(20, 143, 404, 19);
		contentPane.add(lbl_mesaj);
		
		JLabel lbl_bakiye = new JLabel(bakiye());
		lbl_bakiye.setFont(new Font("SansSerif", Font.BOLD, 13));
		lbl_bakiye.setBounds(20, 11, 239, 19);
		contentPane.add(lbl_bakiye);
		
		
		JButton btnek = new JButton("Çek");
		btnek.addActionListener(new ActionListener() {
                    private Object KimlikVeri;
                    @Override
			public void actionPerformed(ActionEvent e) {
				String tc = KimlikVeri.getTc();
				String sifre = KimlikVeri.getSifre();
				
		        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
		            Gson gson = new Gson();
		            java.lang.reflect.Type listType = new TypeToken<List<kullaniciVerileri>>() {
                                private java.lang.reflect.Type getType() {
                                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                                }
                            }.getType();
		            List<kullaniciVerileri> customers = gson.fromJson(reader, listType);
		            
		            boolean cekme = false;
		            
		            
		            for (kullaniciVerileri customer : customers) {
		                if (tc.equals(customer.getTcKimlik()) && sifre.equals(customer.getSifre())) {
		                    lbl_bakiye.setText("Güncel bakiyeniz : "+String.valueOf(customer.getBakiye()));
		                    
		                    double miktar =Double.parseDouble(textField.getText());

		                    if (miktar <= customer.getBakiye()) {
		                        customer.setBakiye(customer.getBakiye() - miktar);
		                        lbl_mesaj.setText("Bakiyeniz Güncellendi");
		                        cekme = true;
		                        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
		                            gson.toJson(customers, writer);
		                        }
		                    } 
		                }
		            }
		            if(!cekme) {
		            	JOptionPane.showMessageDialog(null, "Hatalı Giriş");
                    	lbl_mesaj.setText("Tekrar Deneyiniz");
		            }

		        } catch (Exception e1) {
		        }
			}
		});
		btnek.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnek.setBounds(156, 99, 128, 33);
		contentPane.add(btnek);
		
		JButton btnParaYatr = new JButton("Para Yatır");
		btnParaYatr.addActionListener((ActionEvent e) -> {
                    ParaYatir paraYatir = new ParaYatir();
                    paraYatir.setVisible(true);
                    dispose();
                });
		btnParaYatr.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnParaYatr.setBounds(84, 173, 128, 33);
		contentPane.add(btnParaYatr);
		
		JButton btnBakiyeren = new JButton("Bakiye Öğren");
		btnBakiyeren.addActionListener((ActionEvent e) -> {
                    BakiyeOgren bakiye = new BakiyeOgren();
                    bakiye.setVisible(true);
                    dispose();
                });
		btnBakiyeren.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnBakiyeren.setBounds(84, 217, 128, 33);
		contentPane.add(btnBakiyeren);
		
		JButton btnParaTransfer = new JButton("Para Transfer");
		btnParaTransfer.addActionListener((ActionEvent e) -> {
                    TransferYap transferYap = new TransferYap();
                    transferYap.setVisible(true);
                    dispose();
                });
		btnParaTransfer.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnParaTransfer.setBounds(222, 217, 138, 33);
		contentPane.add(btnParaTransfer);
		
		JButton btnKredideme = new JButton("Kredi Ödeme");
		btnKredideme.addActionListener((ActionEvent e) -> {
                    KrediOdeme krediOdeme = new KrediOdeme();
                    krediOdeme.setVisible(true);
                    dispose();
                });
		btnKredideme.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnKredideme.setBounds(222, 173, 138, 33);
		contentPane.add(btnKredideme);
		
		JLabel lblekmekIstediinizTutar = new JLabel("Çekmek İstediğiniz Tutarı Giriniz : ");
		lblekmekIstediinizTutar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblekmekIstediinizTutar.setBounds(20, 40, 224, 33);
		contentPane.add(lblekmekIstediinizTutar);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(254, 47, 139, 20);
		contentPane.add(textField);		
	}
	public static String bakiye() {
		String tc = KimlikVeri.getTc();
		String sifre = KimlikVeri.getSifre();
		
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            java.lang.reflect.Type listType = new TypeToken<List<kullaniciVerileri>>() {
                private java.lang.reflect.Type getType() {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            }.getType();
            List<kullaniciVerileri> customers = gson.fromJson(reader, listType);
            
            for (kullaniciVerileri customer : customers) {
                if (tc.equals(customer.getTcKimlik()) && sifre.equals(customer.getSifre())) {
                	
                	String text = String.valueOf("Hesabınızdaki Tutar "+customer.getBakiye()+" TL");
                	
                	return text;
                }
            }

            System.out.println("Giriş yapılan hesap bulunamadı.");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return "";
	}

    private static class Gson {

        public Gson() {
        }

        private List<kullaniciVerileri> fromJson(FileReader reader, java.lang.reflect.Type listType) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void toJson(List<kullaniciVerileri> customers, FileWriter writer) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class kullaniciVerileri<T> {

        public kullaniciVerileri() {
        }

        private String getBakiye() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getSifre() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getTcKimlik() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class KimlikVeri {

        private static String getSifre() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static String getTc() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public KimlikVeri() {
        }
    }

    private static class TypeToken<T> {

        public TypeToken() {
        }
    }
	
}