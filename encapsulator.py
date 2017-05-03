#!/usr/bin/python3
# coding = utf-8

import sys
import argparse
import csv



def receive_args():
	parser = argparse.ArgumentParser(description = 'Module fraction for an educational data analyser. It receives a log file and creates session capsules with any actions performed on each access')
	parser.add_argument('-l', '--logfile', help = 'Path to CSV log file with data about actions performed the users of the educational system  (ex.: /home/user/logs.csv | default: logs.csv on current directory). ', default = './logs.csv')
	parser.add_argument('-o', '--output', help = 'Path to output file. (ex.: /home/user/sessions.csv | default: sessions.csv on current directory)',
		default = './sessions.csv')
	
	args = parser.parse_args()

	return args

def unicode_csv_reader(utf8_data, dialect=csv.excel, **kwargs):
    csv_reader = csv.reader(utf8_data, dialect=dialect, **kwargs)
    for row in csv_reader:
        yield [unicode(cell, 'utf-8') for cell in row]

def read_logs(logfilename):
	try:
		csv_reader = unicode_csv_reader(open(logfilename))
	except:
		sys.exit('ERROR - please check the file '+logfilename)


if __name__ == '__main__':
	args = receive_args()
	logs = read_logs(args.logfile)
