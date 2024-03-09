package bank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class KrediOdeme extends JFrame {

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
					KrediOdeme frame = new KrediOdeme();
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
	public KrediOdeme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnParaTransfer = new JButton("Para Transfer");
		btnParaTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferYap transferYap = new TransferYap();
				transferYap.setVisible(true);
				dispose();
			}
		});
		btnParaTransfer.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnParaTransfer.setBounds(209, 217, 138, 33);
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
		btnBakiyeren.setBounds(71, 217, 128, 33);
		contentPane.add(btnBakiyeren);
		
		JButton btnNewButton = new JButton("Para Çek");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaCek paraCek = new ParaCek();
				paraCek.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnNewButton.setBounds(71, 173, 128, 33);
		contentPane.add(btnNewButton);
		
		JButton btnParaYatr = new JButton("Para Yatır");
		btnParaYatr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaYatir paraYatir = new ParaYatir();
				paraYatir.setVisible(true);
				dispose();
			}
		});
		btnParaYatr.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnParaYatr.setBounds(209, 173, 138, 33);
		contentPane.add(btnParaYatr);
		
		JLabel lblNewLabel = new JLabel("Kalan Kredi Borcunuz :");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel.setBounds(21, 25, 163, 33);
		contentPane.add(lblNewLabel);
		 String tc = KimlikVeri.getTc();
         String sifre = KimlikVeri.getSifre();
		JLabel lblNewLabel_1 = new JLabel(krediBorcunuGoster(tc, sifre));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel_1.setBounds(194, 25, 103, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lbldeyeceinizTutarGiriniz = new JLabel("Ödeyeceğiniz Tutarı Giriniz :");
		lbldeyeceinizTutarGiriniz.setFont(new Font("SansSerif", Font.BOLD, 13));
		lbldeyeceinizTutarGiriniz.setBounds(21, 76, 196, 33);
		contentPane.add(lbldeyeceinizTutarGiriniz);
		
		textField = new JTextField();
		textField.setBounds(227, 83, 121, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnde = new JButton("Öde");
		btnde.addActionListener(new ActionListener() {
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
		            Double odenecekTutar = Double.valueOf(textField.getText());

		            for (kullaniciVerileri customer : customers) {
		                if (tc.equals(customer.getTcKimlik()) && sifre.equals(customer.getSifre())) {
		                	borcOdeme(tc, sifre,odenecekTutar);               
		                	
		                }
		            }
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
			}
		});
		btnde.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnde.setBounds(137, 120, 128, 33);
		contentPane.add(btnde);
	}
	  public static void borcOdeme(String tc, String sifre, double odenecekTutar) {
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
	                	if (customer.getBakiye()>=odenecekTutar) {
	                		if (odenecekTutar <= customer.getKartBorc()) {
		                        if(odenecekTutar>=customer.getKartBorc()/3)
		                        {
		                        	customer.setKartBorc(customer.getKartBorc() - odenecekTutar);
		                        	customer.setBakiye(customer.getBakiye()- odenecekTutar);
		                        	JOptionPane.showMessageDialog(null,"Başarıyla borcunuz ödenmiştir kalan borcunuz : "+customer.getKartBorc());
		                        	try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
			                            gson.toJson(customers, writer);
			                        }
		                        	break;
		                        }
		                        else {
									JOptionPane.showMessageDialog(null,"Borcunuzun en az 3/1'ini ödeyebilirsiniz.");
								}
		                        
		                    } else {
		                       JOptionPane.showMessageDialog(null, "Kart borcunuzdan fazla ödeyemezsiniz");
		                    }
						}
	                	else {
	                		JOptionPane.showMessageDialog(null, "Kart limitinizden fazla tutarda ödeme yapamazsınız");
	                	}
	                    
	                    
	                    return; 
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  public static String krediBorcunuGoster(String tc, String sifre) {
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
	                	return String.valueOf(customer.getKartBorc());
					}    
	             }
	            return "HAVE A PROBLEM";
	            }catch (Exception e) {
	            e.printStackTrace();
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

    private static class kullaniciVerileri {

        public kullaniciVerileri() {
        }

        private Object getTcKimlik() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getSifre() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getKartBorc() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private double getBakiye() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setBakiye(double d) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class TypeToken<T> {

        public TypeToken() {
        }
    }

    private static class KimlikVeri {

        private static String getTc() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static String getSifre() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public KimlikVeri() {
        }
    }
}