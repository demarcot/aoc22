package com.tcd.aoc22.day17;

import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.Node;

public class Shape {
  private Node<Integer> root;
  private Node<Integer> top;
  private Node<Integer> bot;
  private Node<Integer> left;
  private Node<Integer> right;
  private List<Node<Integer>> nodes = new ArrayList<>();
  
  public Shape(Node<Integer> root) {
    this.root = root;
    nodes.add(root);
    this.top = root;
    this.bot = root;
    this.left = root;
    this.right = root;
  }
  
  public List<Node<Integer>> getNodes () {
    return this.nodes;
  }
  
  public void addNodes (List<Node<Integer>> nodes) {
    for (var n : nodes) {
      this.addNode(n);
    }
  }
  
  public void addNode(Node<Integer> node) {
    for (var n : this.nodes) {
      if (n.getCoord().isCardinalNeighbor(node.getCoord())) {
        if (n.getCoord().x + 1 == node.getCoord().x) {
          n.addRelative("right", node);
          node.addRelative("left", n);
        } else if (n.getCoord().x - 1 == node.getCoord().x) {
          n.addRelative("left", node);
          node.addRelative("right", n);
        } else if (n.getCoord().y + 1 == node.getCoord().y) {
          n.addRelative("up", node);
          node.addRelative("down", n);
        } else if (n.getCoord().y - 1 == node.getCoord().y) {
          n.addRelative("down", node);
          node.addRelative("up", n);
        }
      }
    }
    this.nodes.add(node);
    
    if (node.getCoord().y > this.top.getCoord().y) {
      this.top = node;
    }
    
    if (node.getCoord().y < this.bot.getCoord().y) {
      this.bot = node;
    }
    
    if (node.getCoord().x > this.right.getCoord().x) {
      this.right = node;
    }
    
    if (node.getCoord().x < this.left.getCoord().x) {
      this.left = node;
    }
  }
  
  public void move (int xDiff, int yDiff) {
    for (var n : this.nodes) {
      n.getCoord().x += xDiff;
      n.getCoord().y += yDiff;
    }
  }
  
  public Node<Integer> getTop () {
    return this.top;
  }
  
  public Node<Integer> getBot () {
    return this.bot;
  }
  
  public Node<Integer> getLeft () {
    return this.left;
  }
  
  public Node<Integer> getRight () {
    return this.right;
  }
  
  public String toString () {
    var w = 1 + this.right.getCoord().x - this.left.getCoord().x;
    var h = 1 + this.top.getCoord().y - this.bot.getCoord().y;
    List<List<String>> strGrid = new ArrayList<>();
    for (int y = 0; y < h; y++) {
      strGrid.add(new ArrayList<>());
      for (int x = 0; x < w; x++) {
        strGrid.get(y).add(".");
      }
    }
    
    for (var n : this.nodes) {
      strGrid.get(n.getCoord().y - this.bot.getCoord().y).set(n.getCoord().x - this.left.getCoord().x, n.getVal() != 0 ? "#" : ".");
    }
    
    var str = "";
    for (int i = strGrid.size(); i > 0; i--) {
      var r = strGrid.get(i - 1);
      for (var c : r) {
        str += c;
      }
      str += "\n";
    }
    return str;
  }
}
