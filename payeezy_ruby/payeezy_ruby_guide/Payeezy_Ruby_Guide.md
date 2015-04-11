# Payeezy Ruby Guide

The Payeezy gem allows you to integrate the different services available in the payeezy line within your Ruby on Rails application.

Ensure that you have the following gems already installed on your development environtment prior to start your development process and in any other environment prior to deployment.
    * Bundler v ~> 1.6
    * Rake
    * RSpec
    * Rest-client v ~> 1.4
    * Json v ~> 1.8.1

Once you ensure all of the dependencies are installed run the following command to install the Gem on your system.
```sh
$ gem install payeezy
```

Allow sometime for the gem to be install. This may take more or less time depending on your connection.

Then run the following command to ensure that the gem is installed

```sh
$ gem list
```
The list command will provide you with the list of all of your gems in the system and payeezy (1.1.0) will be a part of that list.

#Use Payeezy gem in Rails Application

Once you have installed the gem in your system and you have created the rails application, open your Gemfile and enter the following line
```sh
gem 'payeezy'
```
Save the file and then from the root of your rails application issue the following command to integrate the gem and make it available to your rails application.

```sh
$ bundle
```
This command will ensure that payeezy and its dependencies are all available to your rails application. To ensure that the payeezy gem is available to your rails application, run the following command
```sh
$ bundle list
```
The * payeezy (1.1.0) gem will be on the list, making it ready and available to your rails application.


To view the Payeezy API reference documentation and what is available to your application please visit the our [developer site] [1].

For further convenience create an account at our [developer site] [2] to be able to have a sandbox to test your application with and while you are there go ahead and certify as a payeezy developer to take advantage of using our solution for yourself as well as bring your merchants faster and easier than any other payment solution.

[1]:https://developer.payeezy.com/payeezy-api-reference/apis
[2]:https://developer.payeezy.com/