# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'payeezy/version'

Gem::Specification.new do |spec|
  spec.name          = "payeezy"
  spec.version       = Payeezy::VERSION
  spec.authors       = ["Sachin Shetty"]
  spec.email         = ["sachin.shetty@firstdata.com"]
  spec.summary       = %q{Transact with Payeezy}
  spec.description   = %q{See how easy it is to integrate with Payeezy using this gem}
  spec.homepage      = "https://developer.payeezy.com"
  spec.license       = "MIT"

  spec.files         = `git ls-files -z`.split("\x0")
  spec.executables   = spec.files.grep(%r{^bin/}) { |f| File.basename(f) }
  spec.test_files    = spec.files.grep(%r{^(test|spec|features)/})
  spec.require_paths = ["lib"]

  spec.add_development_dependency "bundler", "~> 1.6"
  spec.add_development_dependency "rake"
  spec.add_development_dependency 'rspec'

  spec.add_dependency('rest-client', '~> 1.4')
  spec.add_dependency('json', '~> 1.8.1')

end
