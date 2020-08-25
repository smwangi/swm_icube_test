<?php

function curl_contents($url, $params = null, $files = null)
{

    $curl = curl_init();
    $user_agent = 'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.1.4322)';

    curl_setopt($curl, CURLOPT_URL, $url); //The URL to fetch. This can also be set when initializing a session with curl_init().
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, TRUE); //TRUE to return the transfer as a string of the return value of curl_exec() instead of outputting it out directly.
    curl_setopt($curl, CURLOPT_CONNECTTIMEOUT, 5); //The number of seconds to wait while trying to connect.	


    $post_data = '';

    if (is_array($params) and count($params) > 0) {
        foreach ($params as $k => $v) {
            $post_data .= $k . '=' . $v . '&';
        }
        rtrim($post_data, '&');

        curl_setopt($curl, CURLOPT_POST, count($params));
        curl_setopt($curl, CURLOPT_POSTFIELDS, $post_data);
    }


    if (is_array($files) and count($files) > 0) {
        curl_setopt($curl, CURLOPT_POST, true);
        foreach ($files as $file) {
            curl_setopt(
                $curl,
                CURLOPT_POSTFIELDS,
                array(
                    'file' => '@' . $file
                )
            );
        }
    }


    curl_setopt($curl, CURLOPT_USERAGENT, $user_agent); //The contents of the "User-Agent: " header to be used in a HTTP request.
    curl_setopt($curl, CURLOPT_FAILONERROR, TRUE);  //To fail silently if the HTTP code returned is greater than or equal to 400.
    curl_setopt($curl, CURLOPT_FOLLOWLOCATION, TRUE); //To follow any "Location: " header that the server sends as part of the HTTP header.
    curl_setopt($curl, CURLOPT_AUTOREFERER, TRUE); //To automatically set the Referer: field in requests where it follows a Location: redirect.
    curl_setopt($curl, CURLOPT_TIMEOUT, 10); //The maximum number of seconds to allow cURL functions to execute.	

    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, FALSE); //get contents of secure pages
    curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, FALSE);

    $contents = curl_exec($curl);
    curl_close($curl);
    return $contents;
}
?>