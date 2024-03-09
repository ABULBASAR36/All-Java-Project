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

public class TransferYap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferYap frame = new TransferYap();
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
	
	public TransferYap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnYatr = new JButton("Yatır");
		btnYatr.addActionListener(new ActionListener() {
                    private Object KimlikVeri;
			public void actionPerformed(ActionEvent e) {
				
		        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
		            Gson gson = new Gson();
		            java.lang.reflect.Type listType = new TypeToken<List<kullaniciVerileri>>() {
                                private java.lang.reflect.Type getType() {
                                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                                }
                            }.getType();
		            List<kullaniciVerileri> customers = gson.fromJson(reader, listType);
		            String tc = KimlikVeri.getTc();
		            String sifre = KimlikVeri.getSifre();
		            String aliciHesapNumara = String.valueOf(textField.getText());
		            Double yollanacakMiktar = Double.parseDouble(textField_1.getText());

		            for (kullaniciVerileri customer : customers) {
		                if (tc.equals(customer.getTcKimlik()) && sifre.equals(customer.getSifre())) {
		                	performTransfer(customers, tc, aliciHesapNumara, yollanacakMiktar);                  
		                	
		                }
		            }
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
				
				
			}
		});
		btnYatr.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnYatr.setBounds(157, 99, 128, 33);
		contentPane.add(btnYatr);
		
		JButton btnNewButton = new JButton("Para Çek");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaCek paraCek = new ParaCek();
				paraCek.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnNewButton.setBounds(85, 173, 128, 33);
		contentPane.add(btnNewButton);
		
		JButton btnBakiyeren = new JButton("Bakiye Öğren");
		btnBakiyeren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BakiyeOgren bakiye = new BakiyeOgren();
				bakiye.setVisible(true);
				dispose();
			}
		});
		btnBakiyeren.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnBakiyeren.setBounds(85, 217, 128, 33);
		contentPane.add(btnBakiyeren);
		
		JButton btnParaYatr = new JButton("Para Yatır");
		btnParaYatr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaYatir paraYatir = new ParaYatir();
				paraYatir.setVisible(true);
				dispose();
			}
		});
		btnParaYatr.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnParaYatr.setBounds(223, 217, 138, 33);
		contentPane.add(btnParaYatr);
		
		JButton btnKredideme = new JButton("Kredi Ödeme");
		btnKredideme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KrediOdeme krediOdeme = new KrediOdeme();
				krediOdeme.setVisible(true);
				dispose();
			}
		});
		btnKredideme.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnKredideme.setBounds(223, 173, 138, 33);
		contentPane.add(btnKredideme);
		
		JLabel lblTransferYaplacakHesap = new JLabel("Transfer Yapılacak Hesap No Giriniz : ");
		lblTransferYaplacakHesap.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTransferYaplacakHesap.setBounds(20, 11, 247, 33);
		contentPane.add(lblTransferYaplacakHesap);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(264, 18, 139, 20);
		contentPane.add(textField);
		
		JLabel lblTransferYaplacakTutar = new JLabel("Transfer Yapılacak Tutarı Giriniz :");
		lblTransferYaplacakTutar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTransferYaplacakTutar.setBounds(20, 55, 247, 33);
		contentPane.add(lblTransferYaplacakTutar);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(264, 62, 139, 20);
		contentPane.add(textField_1);
	}
	public static void performTransfer(List<kullaniciVerileri> customers, String gonderenTc, String aliciHesapNumara, double transferMiktar) {
        for (kullaniciVerileri gonderen : customers) {
            if (gonderen.getTcKimlik().toLowerCase().equals(gonderenTc)) {
                for (kullaniciVerileri alici : customers) {
                    if (alici.getHesapNumarasi().equals(aliciHesapNumara)) {
                        if (gonderen.getBakiye() >= transferMiktar) {
                            gonderen.setBakiye(gonderen.getBakiye() - transferMiktar);
                            alici.setBakiye(alici.getBakiye() + transferMiktar);

                            try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                                Gson gson = new Gson();
                                gson.toJson(customers, writer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, alici.getFirstName()+" "+alici.getLastName()+ " adlı kişiye "
                            		+ "" + transferMiktar + " TL Para Transferiniz Başarıyla Gerçekleştirildi."); 
                        } else {
                            JOptionPane.showMessageDialog(null, "Hatalı Giriş");
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null,"Alıcı hesap bulunamadı.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Gönderen hesap bulunamadı.");
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

    private static class kullaniciVerileri {

        public kullaniciVerileri() {
        }

        private String getLastName() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private String getFirstName() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private double getBakiye() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setBakiye(double d) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getHesapNumarasi() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getTcKimlik() {
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

}
