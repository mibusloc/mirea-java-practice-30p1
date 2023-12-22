package ru.mirea.lab30p1.task2;

import java.util.PriorityQueue;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String inputString = "This is a test string.";
        HashMap<Character, Integer> frequencyMap = buildFrequencyMap(inputString);
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        HashMap<Character, String> huffmanCodes = generateHuffmanCodes(root);

        System.out.println("Original String: " + inputString);
        System.out.println("Huffman Codes: " + huffmanCodes);

        String encodedString = encode(inputString, huffmanCodes);
        System.out.println("Encoded String: " + encodedString);

        String decodedString = decode(encodedString, root);
        System.out.println("Decoded String: " + decodedString);
    }

    private static HashMap<Character, Integer> buildFrequencyMap(String input) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    private static HuffmanNode buildHuffmanTree(HashMap<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

        for (char c : frequencyMap.keySet()) {
            priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode internalNode = new HuffmanNode('\0', left.frequency + right.frequency);
            internalNode.left = left;
            internalNode.right = right;

            priorityQueue.add(internalNode);
        }

        return priorityQueue.poll();
    }

    private static HashMap<Character, String> generateHuffmanCodes(HuffmanNode root) {
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateCodesRecursive(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static void generateCodesRecursive(HuffmanNode node, String code, HashMap<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.data != '\0') {
            huffmanCodes.put(node.data, code);
        }

        generateCodesRecursive(node.left, code + "0", huffmanCodes);
        generateCodesRecursive(node.right, code + "1", huffmanCodes);
    }

    private static String encode(String input, HashMap<Character, String> huffmanCodes) {
        StringBuilder encodedString = new StringBuilder();
        for (char c : input.toCharArray()) {
            encodedString.append(huffmanCodes.get(c));
        }
        return encodedString.toString();
    }

    private static String decode(String encodedString, HuffmanNode root) {
        StringBuilder decodedString = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedString.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.data != '\0') {
                decodedString.append(current.data);
                current = root;
            }
        }
        return decodedString.toString();
    }
}

