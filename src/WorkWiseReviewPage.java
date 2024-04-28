import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WorkWiseReviewPage extends JFrame {

    private Map<String, String> userReviews = new HashMap<>();

    public WorkWiseReviewPage(String company) {
        setTitle("WorkWise Review Page");
        setSize(900, 675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addHeader(company);
        addMockReviews();
        collectUserReview(company);

        setVisible(true);
    }

    private void addHeader(String company) {
        JPanel headerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel headerLabel = new JLabel("WorkWise");
        headerLabel.setFont(new Font("Open Sans", Font.BOLD, 22));
        headerLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        headerPanel.add(headerLabel, gbc);

        JLabel companyName = new JLabel(company);
        companyName.setFont(new Font("Open Sans", Font.BOLD, 14));
        gbc.gridy = 1;
        headerPanel.add(companyName, gbc);

        add(headerPanel, BorderLayout.NORTH);
    }

    private void addMockReviews() {
        JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));

        JLabel reviewsLabel = new JLabel("Previous Reviews:");
        reviewsPanel.add(reviewsLabel);

        String[] mockReviews = {
                "1. Great company to work with. Highly recommended! - ★★★★★",
                "2. Good environment and supportive team. - ★★★★☆",
                "3. Could improve communication within teams. - ★★★☆☆",
                "4. Excellent work-life balance. - ★★★★★"
        };

        for (String review : mockReviews) {
            JLabel reviewLabel = new JLabel(review);
            reviewsPanel.add(reviewLabel);
        }

        add(reviewsPanel, BorderLayout.CENTER);
    }

    private void collectUserReview(String company) {
        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new GridLayout(5, 1));

        JLabel userReviewLabel = new JLabel("Please type your review of " + company + ":");
        JTextArea userReviewTextArea = new JTextArea(5, 20);
        JScrollPane reviewScrollPane = new JScrollPane(userReviewTextArea);

        reviewPanel.add(userReviewLabel);
        reviewPanel.add(reviewScrollPane);

        JLabel ratingLabel = new JLabel("Please rate " + company +  " out of 5 stars:");
        JPanel ratingPanel = new JPanel();

        ButtonGroup starsGroup = new ButtonGroup();

        ActionListener ratingListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This listener only updates the rating when a star is selected
                // No submission logic here
            }
        };

        for (int i = 1; i <= 5; i++) {
            JRadioButton starButton = new JRadioButton(String.valueOf(i));
            starButton.setActionCommand(String.valueOf(i));
            starButton.addActionListener(ratingListener);
            starsGroup.add(starButton);
            ratingPanel.add(starButton);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This listener handles the submission when the submit button is clicked
                String review = userReviewTextArea.getText();
                String selectedRating = starsGroup.getSelection().getActionCommand();

                if (!review.isEmpty() && selectedRating != null) {
                    if (!userReviews.containsKey("Your Review")) {
                        userReviews.put("Your Review", review + " - " + getStars(Integer.parseInt(selectedRating)));
                        updateReviewsPanel();
                    } else {
                        JOptionPane.showMessageDialog(null, "You have already submitted a review.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter your review and rate " + company + " before submitting.");
                }
            }
        });

        reviewPanel.add(ratingLabel);
        reviewPanel.add(ratingPanel);
        reviewPanel.add(submitButton);

        add(reviewPanel, BorderLayout.SOUTH);
    }



    private void updateReviewsPanel() {
        JPanel reviewsPanel = (JPanel) getContentPane().getComponent(1);
        JLabel yourReviewsLabel = new JLabel("Your Reviews:");
        reviewsPanel.add(yourReviewsLabel);
        for (Map.Entry<String, String> entry : userReviews.entrySet()) {
            JLabel reviewLabel = new JLabel(entry.getKey() + " - " + entry.getValue());
            reviewsPanel.add(reviewLabel);
        }
        getContentPane().remove(1);
        getContentPane().add(reviewsPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private String getStars(int rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        for (int i = rating; i < 5; i++) {
            stars.append("☆");
        }
        return stars.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WorkWiseReviewPage("Google");
            }
        });
    }
}
