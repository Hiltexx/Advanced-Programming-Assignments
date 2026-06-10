import React, { useState } from 'react';

import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet
} from 'react-native';

function App(): React.JSX.Element {

  const [count, setCount] = useState(0);

  const [isDarkMode, setIsDarkMode] = useState(false);

  const handleIncrement = () => {
    setCount(count + 1);
  };

  const handleDecrement = () => {

    if (count > 0) {
      setCount(count - 1);
    }
  };

  const handleReset = () => {
    setCount(0);
  };

  const toggleTheme = () => {
    setIsDarkMode(!isDarkMode);
  };

  return (

    <View
      style={[
        styles.container,
        {
          backgroundColor:
            isDarkMode ? '#121212' : '#FFFFFF'
        }
      ]}
    >

      <Text
        style={[
          styles.title,
          {
            color:
              isDarkMode ? '#FFFFFF' : '#000000'
          }
        ]}
      >
        Simple Digital Counter
      </Text>

      <Text
        style={[
          styles.counterText,
          {
            color:
              isDarkMode ? '#FFFFFF' : '#000000'
          }
        ]}
      >
        {count}
      </Text>

      <View style={styles.buttonRow}>

        <TouchableOpacity
          style={styles.button}
          onPress={handleIncrement}
        >
          <Text style={styles.buttonText}>
            Increment
          </Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.button}
          onPress={handleDecrement}
        >
          <Text style={styles.buttonText}>
            Decrement
          </Text>
        </TouchableOpacity>

      </View>

      <TouchableOpacity
        style={styles.button}
        onPress={handleReset}
      >
        <Text style={styles.buttonText}>
          Reset
        </Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={styles.themeButton}
        onPress={toggleTheme}
      >
        <Text style={styles.buttonText}>
          Toggle Theme
        </Text>
      </TouchableOpacity>

    </View>
  );
}

const styles = StyleSheet.create({

  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20
  },

  title: {
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 20
  },

  counterText: {
    fontSize: 60,
    fontWeight: 'bold',
    marginBottom: 40
  },

  buttonRow: {
    flexDirection: 'row',
    marginBottom: 20
  },

  button: {
    backgroundColor: '#3498db',
    paddingVertical: 12,
    paddingHorizontal: 20,
    borderRadius: 10,
    marginHorizontal: 10,
    marginVertical: 10
  },

  themeButton: {
    backgroundColor: '#2ecc71',
    paddingVertical: 12,
    paddingHorizontal: 20,
    borderRadius: 10,
    marginTop: 20
  },

  buttonText: {
    color: '#FFFFFF',
    fontSize: 18,
    fontWeight: 'bold'
  }
});

export default App;
