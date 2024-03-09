package bank;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;




public class BakiyeOgren extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BakiyeOgren frame = new BakiyeOgren();
					
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
	
	
	 
	public BakiyeOgren() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hesabınızda Bulunan Bakiye Miktarı ");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel.setBounds(78, -12, 280, 70);
		contentPane.add(lblNewLabel);
		
		JLabel bk_ogren = new JLabel("");
		bk_ogren.setVerticalAlignment(SwingConstants.TOP);
		bk_ogren.setFont(new Font("SansSerif", Font.BOLD, 15));
		bk_ogren.setBounds(142, 40, 128, 70);
		contentPane.add(bk_ogren);
		
		
		JButton btnNewButton = new JButton("Para Çek");
		btnNewButton.addActionListener((ActionEvent e) -> {
                    ParaCek paraCek = new ParaCek();
                    paraCek.setVisible(true);
                    dispose();
                });
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnNewButton.setBounds(78, 173, 117, 33);
		contentPane.add(btnNewButton);
		
		JButton btnParaYatr = new JButton("Para Yatır");
		btnParaYatr.addActionListener((ActionEvent e) -> {
                    ParaYatir paraYatir = new ParaYatir();
                    paraYatir.setVisible(true);
                    dispose();
                });
		btnParaYatr.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnParaYatr.setBounds(205, 173, 128, 33);
		contentPane.add(btnParaYatr);
		
		JButton btnKredideme = new JButton("Kredi Ödeme");
		btnKredideme.addActionListener((ActionEvent e) -> {
                    KrediOdeme krediOdeme = new KrediOdeme();
                    krediOdeme.setVisible(true);
                    dispose();
                });
		btnKredideme.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnKredideme.setBounds(78, 217, 117, 33);
		contentPane.add(btnKredideme);
		
		JButton btnParaTransfer = new JButton("Para Transfer");
		btnParaTransfer.addActionListener((ActionEvent e) -> {
                    TransferYap transferYap = new TransferYap();
                    transferYap.setVisible(true);
                    dispose();
                });
		btnParaTransfer.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnParaTransfer.setBounds(205, 217, 128, 33);
		contentPane.add(btnParaTransfer);
		
		
		
		JButton btnBakiyeniren = new JButton("Bakiyeni Öğren");
		btnBakiyeniren.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String tc=KimlikVeri.getTc();
                        String sifre = KimlikVeri.getSifre();
                        try (final FileReader reader = new FileReader(JSON_FILE_PATH)) {
                            Gson gson = new Gson();
                            java.lang.reflect.Type listType;
                            listType = // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                                    new TypeToken<List<kullaniciVerileri>>() {
                                        private Object BakiyeOgren;
                                        () {
                                            {
                                                BakiyeOgren.super();
                                                return null;
                                            }
                                            
                                            private java.lang.reflect.Type getType() {
                                                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                                            }
                                    }.getType();
                            List<kullaniciVerileri> customers = gson.fromJson(reader, listType);
                            for(kullaniciVerileri customer : customers){
                                if(tc.equals(customer.getTcKimlik()) && sifre.equals(customer.getSifre())) {
                                    bk_ogren.setText(String.valueOf(customer.getBakiye()));
                                }
                                
                            }
                        }catch (Exception e1) {
                        }
                    }
                });
		btnBakiyeniren.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnBakiyeniren.setBounds(142, 121, 138, 33);
		contentPane.add(btnBakiyeniren);
		
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

    private static class kullaniciVerileri<T> {

        public kullaniciVerileri() {
        }

        private Object getTcKimlik() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getSifre() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getBakiye() {
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

        private List<kullaniciVerileri> fromJson(FileReader reader, java.lang.reflect.Type listType) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
	

}