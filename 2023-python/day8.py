from helpers import *
import re
import numpy

def part1():
  lines = read_lines('day8.txt')
  moves = lines[0].strip()
  network = {}
  for i in range(2, len(lines)):
    matches = re.search("(.*) = \((.*), (.*)\)", lines[i])
    network[matches.group(1)] = {'left': matches.group(2), 'right': matches.group(3)}
  step_count = 0
  current_location = 'AAA'
  while current_location != 'ZZZ':
    current_index = step_count % len(moves)
    next_move = moves[current_index]
    current_location = network[current_location]['left'] if next_move == 'L' else network[current_location]['right']
    step_count += 1
  print(step_count)

def part2():
  lines = read_lines('day8.txt')
  moves = lines[0].strip()
  network = {}
  current_locations = []
  for i in range(2, len(lines)):
    matches = re.search("(.*) = \((.*), (.*)\)", lines[i])
    network[matches.group(1)] = {'left': matches.group(2), 'right': matches.group(3)}
    if matches.group(1).endswith('A'):
      current_locations.append(matches.group(1))
  step_count = 0
  steps_to_z = numpy.full(len(current_locations), -1)
  while not found_all_zs(steps_to_z):
    current_index = step_count % len(moves)
    next_move = moves[current_index]
    for (index, location) in enumerate(current_locations):
      next_location = network[location]['left'] if next_move == 'L' else network[location]['right']
      current_locations[index] = next_location
      if next_location.endswith('Z') and steps_to_z[index] == -1:
        steps_to_z[index] = step_count + 1
    step_count += 1
  print(steps_to_z)
  print(numpy.lcm.reduce(steps_to_z))

def found_all_zs(steps_to_z):
  for instance in steps_to_z:
    if instance == -1:
      return False
  return True
  
def all_at_end(locations):
  for location in locations:
    if not location.endswith('Z'):
      return False
  return True

part2()