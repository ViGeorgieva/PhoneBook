package PhoneBook;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class Phonebook {
    private final Map<String, PhoneNumber> phonebook = new TreeMap<>();
    private List<Pattern> patterns = new ArrayList<>();
    private int count = 0;

    public int getCount() {
        return count;
    }
    public List<Pattern> getPatterns() {
        return patterns;
    }

    public enum Results {
        ADDED,
        UPDATED,
        INVALID_NUMBER,
        INVALID_NAME
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidPhoneNumber(String number) {

            patterns.add(Pattern.compile("(\\+359)(87|88|89)([2-9])([0-9]{6})"));
            patterns.add(Pattern.compile("(0)(87|88|89)([2-9])([0-9]{6})"));
            patterns.add(Pattern.compile("(00359)(87|88|89)([2-9])([0-9]{6})"));
        return true;
    }


    public boolean readFromFile(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] pair = line.split(", ");
                    if (pair.length == 2) {
                        addContact(pair[0], pair[1]);
                    } else {
                        System.out.println("There is incorrect record!");
                    }
                } catch (PatternSyntaxException e) {
                    System.out.println("There is incorrect record!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Results addContact(String name, String phoneNumber) {
        if (isValidName(name)) {
            if (isValidPhoneNumber(phoneNumber)) {
                Results results;
                PhoneNumber existingContact = phonebook.get(name);

                if (existingContact != null) {
                    existingContact.setPhoneNumber(phoneNumber);
                    results = Results.UPDATED;
                } else {
                    phonebook.put(name, new PhoneNumber(name, phoneNumber));
                    results = Results.ADDED;
                }
                return results;
            } else {
                return Results.INVALID_NUMBER;
            }
        } else {
            return Results.INVALID_NAME;
        }
    }

    public boolean deleteContact(String name) {
        if (phonebook.containsKey(name)) {
            phonebook.remove(name);
            return true;
        } else {
            System.out.println("There is no match with  " + name + "in the phonebook");
            return false;
        }
    }

    public void getTopFive() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>(phonebook.values());
        phoneNumbers.sort(PhoneNumber::compareTo);

        List<PhoneNumber> topFive = phoneNumbers.subList(Math.max(phoneNumbers.size() - 5, 0), phoneNumbers.size());
        System.out.println("\nFive pairs with biggest count of outgoing calls:\n");
        for (PhoneNumber phoneNumber : topFive) {
            System.out.printf("Name: %20s || Phone: %s || Count: %d\n", phonebook.entrySet()
                            .stream()
                            .filter(entry -> Objects.equals(entry.getValue(), phoneNumber))
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList()).get(0),
                    phoneNumber.getPhoneNumber(), phoneNumber.getCount());
        }
    }

    public String viewPhoneNumber(String name) {
        PhoneNumber phoneNumber = phonebook.get(name);
        if (phoneNumber == null) {
            return null;
        }
        phoneNumber.setCount(phoneNumber.getCount() + 1);
        count++;
        return phoneNumber.getPhoneNumber();
    }

    public void printAllSorted() {
        for (Map.Entry<String, PhoneNumber> entry : phonebook.entrySet()) {
            System.out.println("Name: " + entry.getKey()
                    + " Phone: " + entry.getValue().getPhoneNumber());
        }
        System.out.println("All outgoing calls: " + count);
    }

}

