from helpers import *
import re

def part1():
  lines = read_lines('day2.txt')
  sum = 0
  max_red = 12
  max_green = 13
  max_blue = 14
  for line in lines:
    game_num = int(re.search('Game (\\d*): (.*)', line).group(1))
    results = line.split(":")[1]
    games = results.split(";")
    reds = 0
    blues = 0
    greens = 0
    for game in games:
      print(game)
      reds = max(reds, find_num_of_color('red', game))
      blues = max(blues, find_num_of_color('blue', game))
      greens = max(greens, find_num_of_color('green', game))
    if (reds <= max_red and greens <= max_green and blues <= max_blue):
      sum += game_num
  print(sum)

def part2():
  lines = read_lines('day2.txt')
  sum = 0
  for line in lines:
    game_num = int(re.search('Game (\\d*): (.*)', line).group(1))
    results = line.split(":")[1]
    games = results.split(";")
    reds = 0
    blues = 0
    greens = 0
    for game in games:
      reds = max(reds, find_num_of_color('red', game))
      blues = max(blues, find_num_of_color('blue', game))
      greens = max(greens, find_num_of_color('green', game))
    sum += reds * greens * blues
  print(sum)

def find_num_of_color(color, game):
  search_string = '(\\d*) ' + color
  result = re.search(search_string, game)
  if (result != None):
    return int(result.group(1))
  else:
    return 0

part2()