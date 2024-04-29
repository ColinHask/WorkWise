import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompanyReviewPage extends JFrame {
    private final List<Review> reviews; // List of reviews
    private final JTextArea reviewDisplay; // Display area for reviews

    public CompanyReviewPage(String company) {
        // Initialize the list and add sample reviews with ratings
        reviews = new ArrayList<>();
        reviews.add(new Review("Great company with excellent work culture.", LocalDateTime.now().minusDays(5), 5));
        reviews.add(new Review("Interview process was straightforward.", LocalDateTime.now().minusDays(3), 4));
        reviews.add(new Review("Had some issues with HR, but overall a good experience.", LocalDateTime.now().minusDays(7), 3));
        reviews.add(new Review("Amazing benefits and work-life balance.", LocalDateTime.now().minusDays(1), 5));
        reviews.add(new Review("Technical interview was challenging but fair.", LocalDateTime.now().minusDays(6), 4));
        reviews.add(new Review("Friendly team and supportive management.", LocalDateTime.now().minusDays(2), 5));

        // Set up the frame
        setTitle("WorkWise");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        // Title at the top
        JLabel titleLabel = new JLabel("WorkWise", JLabel.CENTER);
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 22));
        titleLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        headerPanel.add(titleLabel, gbc);

        // Section header for "Reviews for Microsoft"
        JLabel sectionHeader = new JLabel("Reviews for " + company, JLabel.CENTER);
        sectionHeader.setFont(new Font("Arial", Font.BOLD, 20));
        sectionHeader.setForeground(Color.BLACK); 
        gbc.gridy = 1;
        headerPanel.add(sectionHeader, gbc);

        mainPanel.add(headerPanel, BorderLayout.NORTH);


        // Panel for displaying reviews with a scroll pane
        reviewDisplay = new JTextArea();
        reviewDisplay.setEditable(false);
        reviewDisplay.setLineWrap(true);
        reviewDisplay.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(reviewDisplay);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for user input and review submission
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Panel for input field and rating selection
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton submitButton = new JButton("Submit A Review");
        submitButton.addActionListener(e -> {
            new CreateReviewPage(company);
        });

        inputPanel.add(submitButton);

        // Add the text box and input panel to the bottom panel
        bottomPanel.add(inputPanel, BorderLayout.SOUTH);

        // Add the bottom panel to the main panel
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Display the initial reviews
        displayReviews();
    }

    // Display the list of reviews in the text area
    private void displayReviews() {
        StringBuilder sb = new StringBuilder();
        for (Review review : reviews) {
            sb.append(String.format("%s (Rating: %d, Date: %s)\n",
                    review.getText(),
                    review.getRating(),
                    review.getDate())); // Display each review
            sb.append(starRating(review.getRating())).append("\n"); // Add star ratings
        }
        reviewDisplay.setText(sb.toString()); // Update the text area with the reviews
    }

    // Generate a star representation for the rating
    private String starRating(int rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        return stars.toString();
    }

    // Main method to start the application

    // Inner class for representing a review
    static class Review {
        private final String text;
        private final LocalDateTime date;
        private final int rating;

        public Review(String text, LocalDateTime date, int rating) {
            this.text = text;
            this.date = date;
            this.rating = rating; 
        }

        public String getText() {
            return text; // Get the review text
        }

        public LocalDateTime getDate() {
            return date; // Get the review date
        }

        public int getRating() {
            return rating; // Get the review rating
        }

        @Override
        public String toString() {
            return String.format("%s (Rating: %d, Date: %s)", text, rating, date); // Textual representation of the review
        }
    }
}
