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

public class ParaYatir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaYatir frame = new ParaYatir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private static final String JSON_FILE_PATH = "C:\\Users\\berka\\OneDrive\\Masaüstü\\bank\\Bank_Management\\src\\veri.json";
	
	public ParaYatir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Para Çek");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaCek paraCek = new ParaCek();
				paraCek.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnNewButton.setBounds(74, 173, 128, 33);
		contentPane.add(btnNewButton);
		
		JButton btnParaTransfer = new JButton("Para Transfer");
		btnParaTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferYap transferYap = new TransferYap();
				transferYap.setVisible(true);
				dispose();
			}
		});
		btnParaTransfer.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnParaTransfer.setBounds(212, 217, 138, 33);
		contentPane.add(btnParaTransfer);
		
		JButton btnBakiyeren = new JButton("Bakiye Öğren");
		btnBakiyeren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BakiyeOgren bakiye = new BakiyeOgren();
				bakiye.setVisible(true);
				dispose();
			}
		});
		btnBakiyeren.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnBakiyeren.setBounds(74, 217, 128, 33);
		contentPane.add(btnBakiyeren);
		
		JButton btnYatr = new JButton("Yatır");
		btnYatr.addActionListener(new ActionListener() {
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
		                   // lbl_bakiye.setText("Güncel bakiyeniz : "+String.valueOf(customer.getBakiye()));
		                    
		                    double miktar =Double.parseDouble(textField.getText());
		                    
		                    if (miktar <= 500 && miktar>=5) {
		                        customer.setBakiye(customer.getBakiye() + miktar);
		                    //    lbl_mesaj.setText("Bakiyeniz Güncellendi");
		                        cekme = true;
		                        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
		                            gson.toJson(customers, writer);
		                        }
		                        JOptionPane.showMessageDialog(null," İşlem başarıyla gerçekleştirildi güncel bakiyeniz : "+customer.getBakiye());

		                    }
		                    else {
								JOptionPane.showMessageDialog(null, "500 TL'den fazla 5 TL'den aşşağıya yükleme yapılamaz");
							}
		                }
		            }
		            if(!cekme) {
		            	JOptionPane.showMessageDialog(null, "Hatalı Giriş");
                    	//lbl_mesaj.setText("Tekrar Deneyiniz");
		            }

		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
				
			}
		});
		btnYatr.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnYatr.setBounds(146, 99, 128, 33);
		contentPane.add(btnYatr);
		
		JLabel lblYatrmakIstediinizTutar = new JLabel("Yatırmak İstediğiniz Tutarı Giriniz : ");
		lblYatrmakIstediinizTutar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblYatrmakIstediinizTutar.setBounds(10, 40, 224, 33);
		contentPane.add(lblYatrmakIstediinizTutar);
		
		textField = new JTextField();
		textField.setBounds(244, 47, 139, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnKredideme = new JButton("Kredi Ödeme");
		btnKredideme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KrediOdeme krediOdeme = new KrediOdeme();
				krediOdeme.setVisible(true);
				dispose();
			}
		});
		btnKredideme.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnKredideme.setBounds(212, 173, 138, 33);
		contentPane.add(btnKredideme);
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

    private static class kullaniciVerileri {

        public kullaniciVerileri() {
        }

        private String getBakiye() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getTcKimlik() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setBakiye(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getSifre() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class TypeToken<T> {

        public TypeToken() {
        }
    }

    private static class Gson {

        public Gson() {
        }

        private void toJson(List<kullaniciVerileri> customers, FileWriter writer) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private List<kullaniciVerileri> fromJson(FileReader reader, java.lang.reflect.Type listType) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

}