## Currency Converter

Welcome to the Currency Converter! This application allows users to fetch and manage currency exchange rates with options to change the base currency and retrieve exchange rates for the latest date, a specific historical date, or a date range.

## Table of Contents
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [File Descriptions](#file-descriptions)
- [Troubleshooting](#troubleshooting)

## Getting Started

This Currency Converter application is implemented in Java and Python. Java is used to create a console-based UI, while Python handles interactions with the currency API to retrieve exchange rates.

### Prerequisites

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) 8 or higher
- [Python](https://www.python.org/downloads/) 3.6 or higher
- A valid API key for the CurrencyAPI (e.g., `cur_live_v0DKfMohFVghIulj5F1ev2hfbJWcrkEGjBVGkAyU`) to use in `api.py`

### Installation

1. Clone or download this repository to your local machine.
2. Set up your environment:
    - Place your API key in `api.py` as shown below:
      ```python
      client = currencyapicom.Client('YOUR_API_KEY_HERE')
      ```
3. Ensure that `base_currency.txt` contains the initial base currency (e.g., "USD").

## Usage

1. Open a terminal in the project folder and compile `currencyConverter.java`:
    ```bash
    javac currencyConverter.java
    ```

2. Run the Java program:
    ```bash
    java currencyConverter
    ```

3. Follow the on-screen prompts to:
    - Change the base currency
    - Retrieve the latest exchange rates
    - Retrieve exchange rates for a historical date
    - Retrieve exchange rates over a date range

### Options Available

- **Enter `0`**: Exit the program.
- **Enter `1`**: Change the base currency.
- **Enter `2`**: Fetch the latest exchange rates.
- **Enter `3`**: Fetch historical exchange rates for a specific date.
- **Enter `4`**: Fetch exchange rates for a range of dates.

## File Descriptions

- **currencyConverter.java**: Main Java program that provides a console-based interface for user interaction and handles communication with `api.py`.
- **api.py**: Python script that interfaces with the CurrencyAPI to retrieve exchange rates and write them to text files.
- **base_currency.txt**: Stores the current base currency for the API requests.
- **latest_data.txt**: Stores the latest currency exchange rates in JSON format.
- **historical_data.txt**: Stores historical exchange rates for a specific date.
- **range_of_rates.txt**: Stores exchange rates over a specified date range.

## Troubleshooting

1. **Python Script Errors**: If the Java program fails to execute the Python script, ensure that:
    - The path to `api.py` is correctly specified in `ProcessBuilder`.
    - You have the necessary Python dependencies installed (e.g., `currencyapicom`, `json`).

2. **API Key Issues**: If you encounter an API error, verify your API key and ensure you have not exceeded your API call limit.

3. **File Not Found**: If the program cannot find `base_currency.txt`, ensure it exists in the same directory as `currencyConverter.java`.
