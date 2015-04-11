import os
import sys

try:
    from setuptools import setup
except ImportError:
    from distutils.core import setup

try:
    from distutils.command.build_py import build_py_2to3 as build_py
except ImportError:
    from distutils.command.build_py import build_py

path, script = os.path.split(sys.argv[0])
os.chdir(os.path.abspath(path))

requests = 'requests >= 2.3.0'
if sys.version_info < (3, 3):
    requests += ', < 0.10.0'
install_requires = [requests]


if sys.version_info < (3, 3):
    install_requires.append('ssl')

# Get json if we don't already have json
if sys.version_info < (3, 3):
    try:
        from util import json
    except ImportError:
        install_requires.append('json')



setup(
    name = "payeezy",
    packages = ["payeezy"],
    version = "1.0",
    description = "Payeezy",
    author = "Payeezy",
    author_email = "admin@payeezy.com",
    url="https://developer.payeezy.com",
    keywords = ["transaction", "encode", "json"],
    classifiers = [
        "Programming Language :: Python",
        "Programming Language :: Python :: 2",
        "Programming Language :: Python :: 3",
        "Development Status :: 3 - Beta",
        "Environment :: Other Environment",
        "Intended Audience :: Developers",
        "Operating System :: OS Independent",
        "Topic :: Software Development :: Libraries :: Python Modules",
        "Topic :: Process Transaction :: Payments",
        ],
    long_description = """\
Payeezy - Process Transactions
-------------------------------------

This is a rest client for Payeezy framework build to ease the process and
transaction time.

Included transaction methods are
- authorize
- purchase
- capture
- void
- refund

This Rest client - Payeezy is compatible with both Python 2.x & Python 3.x
Download and install Requests 2.3 from the link below which is a dependancy for Payeezy.
https://github.com/kennethreitz/requests

"""
)