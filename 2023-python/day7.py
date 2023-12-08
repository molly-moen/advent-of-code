from helpers import *
from functools import total_ordering

#@total_ordering
class Hand:
  card_order = ['A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J']
  def __init__(self, cards, bid):
    self.cards = cards
    self.bid = bid
    self.parse_hand()

  def parse_hand(self):
    self.sorted_cards = sorted(self.cards)
    #print(f"cards: {self.cards} sorted_cards: {self.sorted_cards}")
    num_pairs = 0
    current_matches = 1
    max_duplicate = 1
    num_jokers = 1 if self.sorted_cards[0] == 'J' else 0
    for i in range(1, len(self.sorted_cards)):
      found_match = False
      if self.sorted_cards[i] == 'J':
        num_jokers += 1
      elif self.sorted_cards[i] == self.sorted_cards[i - 1]:
          found_match = True
          current_matches += 1

      if not found_match or i == len(self.sorted_cards) - 1:
        if max_duplicate < current_matches:
          max_duplicate = current_matches
        if current_matches == 2:
          num_pairs += 1
        current_matches = 1
    if num_jokers > 0:
      if max_duplicate == 2:
        num_pairs -= 1
      max_duplicate += num_jokers
      if max_duplicate == 2:
        num_pairs += 1
    
    if max_duplicate <= 1:
      self.type = 1 # high card
    elif max_duplicate == 2:
      self.type = 2 if num_pairs == 1 else 3 # type 2 is 1 pair, type 3 is 2 pair
    elif max_duplicate == 3:
      self.type = 4 if num_pairs == 0 else 5 # type 4 is 3 of a kind, type 5 is full house
    elif max_duplicate == 4:
      self.type = 6 # four of a kind
    else:
      self.type = 7 # five of a kind


    print(f"max_duplicate {max_duplicate} num_pairs {num_pairs} type {self.type}")
    #print()

  def __eq__(self, other):
    return self.cards == other.cards
  
  def __ne__(self, other):
    return self.cards != other.cards
  
  def __lt__(self, other):
    if self.type != other.type:
      return self.type < other.type
    for (index, character) in enumerate(self.cards):
      if (character != other.cards[index]):
        return Hand.card_order.index(character) > Hand.card_order.index(other.cards[index])
    # all characters are the same
    return False
  
  def __le__(self, other):
    if self.type != other.type:
      return self.type <= other.type
    for (index, character) in enumerate(self.cards):
      if (character != other.cards[index]):
        return Hand.card_order.index(character) > Hand.card_order.index(other.cards[index])
    # all characters are the same
    return True
  
  def __gt__(self, other):
    if self.type != other.type:
      return self.type > other.type
    for (index, character) in enumerate(self.cards):
      if (character != other.cards[index]):
        return Hand.card_order.index(character) < Hand.card_order.index(other.cards[index])
    # all characters are the same
    return False
  
  def __ge__(self, other):
    if self.type != other.type:
      return self.type >= other.type
    for (index, character) in enumerate(self.cards):
      if (character != other.cards[index]):
        return Hand.card_order.index(character) < Hand.card_order.index(other.cards[index])
    # all characters are the same
    return True

  def __str__(self):
    return f"{self.cards} {self.bid}"
  
  def __repr__(self):
    return f"{self.cards} {self.bid}"

def get_winnings():
  lines = read_lines('day7.txt')
  hands = []
  for line in lines:
    components = line.strip().split()
    cards = components[0]
    bid = int(components[1])
    hands.append(Hand(cards, bid))
  hands.sort()
  result = 0
  for i in range(0, len(hands)):
    result += hands[i].bid * (i + 1)
  print(hands)
  print(result)



get_winnings()



