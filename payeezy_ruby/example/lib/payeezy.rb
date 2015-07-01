require 'payeezy/version'
require 'rest_client'
require 'date'
require 'json'
require 'digest/sha2'
require 'base64'
require 'securerandom'

module Payeezy
  class Transactions
    def initialize(options = {})
      @url = options[:url]
      @tokenURL = options[:tokenURL]
      @apikey = options[:apikey]
      @apisecret = options[:apisecret]
      @token = options[:token]
    end

    def transact(action, payload)
      url = @url
      commit(url, action, payload)
    end
    
    def transactGetToken(action, payload)
      url = @tokenURL
      commitGetToken(url, action, payload)
    end

    def generate_hmac(nonce, current_timestamp, payload)
      message = @apikey + nonce.to_s + current_timestamp.to_s + @token + payload
      hash = Base64.strict_encode64(bin_to_hex(OpenSSL::HMAC.digest('sha256', @apisecret, message)))
      hash
    end

    def bin_to_hex(s)
      s.unpack('H*').first
    end

    def headers(payload)
      nonce = (SecureRandom.random_number *10000000000)
      current_timestamp = (Time.now.to_f*1000).to_i
      {
          'Content-Type' => 'application/json',
          'apikey' => @apikey,
          'token' => @token,
          'nonce' => nonce,
          'timestamp' => current_timestamp,
          'Authorization' => generate_hmac(nonce, current_timestamp, payload)
      }
    end

    def commit(url, action, params)
      
      if action == :capture || action == :void || action == :refund || action == :split
        url = url + '/' + params[:transaction_id]
        params.delete(:transaction_id)
      end
      params[:transaction_type] = action
      printf "url =%s \n", url 
      printf "url =%s \n", params
      
      call_rest(url, post_data(params), headers(post_data(params)))
    end
    
    def commitGetToken(url, action, params)
     
      printf "url =%s \n", url 
      printf "url =%s \n", params
      call_rest(url, post_data(params), headers(post_data(params)))
    end

    def call_rest(url, data, headers)
      rest_resource = RestClient::Resource.new(url)
      raw_response = response = {}
      begin
        raw_response = rest_resource.post data, headers
        response = parse(raw_response)
      rescue => e
        raw_response = e.response
        response = response_error(raw_response)
      rescue JSON::ParserError
        response = json_error(raw_response)
      end

      response
    end

    def handle_message(response, success)
      if success
        response['transaction_status']
      elsif response.key?('Error')
        response['Error'].map { |_, messages| messages }.join('. ')
      else
        response.inspect
      end
    end

    def response_error(raw_response)
      begin
        parse(raw_response)
      rescue JSON::ParserError
        json_error(raw_response)
      end
    end

    def parse(body)
      JSON.parse(body)
    end

    def post_data(params)
      params.to_json
    end

    def json_error(raw_response)
      msg = "Payeezy has returned an invalid response: [#{raw_response.inspect}]"
      {
          'Error' => {
              'messages' => msg
          }
      }
    end
  end
end
