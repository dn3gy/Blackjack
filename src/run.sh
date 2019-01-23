#!/bin/bash
clear
echo "------------------Running Unit Tests--------------------"
javac -d ./ -cp ../junit-4.12.jar BlackjackTest.java Player.java Card.java Hand.java Deck.java Blackjack.java
java -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar org.junit.runner.JUnitCore BlackjackTest
echo "-------------------End of Unit Tests-------------------"
echo ""
echo "--------------Starting Blackjack Program---------------"
javac Blackjack.java
java Blackjack