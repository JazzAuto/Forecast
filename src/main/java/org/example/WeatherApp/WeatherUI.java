package org.example.WeatherApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherUI {

    private JFrame frame;
    private JTextField cityField;
    private JTextArea resultArea;
    private WeatherService weatherService = new WeatherService();
    private WeatherParser weatherParser = new WeatherParser();

    public WeatherUI()
    {
        frame = new JFrame("Weather Forecast");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel cityLabel = new JLabel("Enter city:");
        cityLabel.setBounds(10, 10, 80, 25);
        frame.add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(100, 10, 165, 25);
        frame.add(cityField);

        JButton getWeatherButton = new JButton("Get Weather");
        getWeatherButton.setBounds(10, 40, 150, 25);
        frame.add(getWeatherButton);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 70, 360, 80);
        frame.add(resultArea);

        getWeatherButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String city = cityField.getText();
                try
                {
                    String response = weatherService.getWeather(city);
                    String parsedResponse = weatherParser.parseWeather(response);
                    resultArea.setText(parsedResponse);
                }
                catch (Exception ex)
                {
                    resultArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new WeatherUI();
    }
}
