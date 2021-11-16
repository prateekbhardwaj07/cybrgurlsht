# cybrgurlsht

Spring MVC based Url Shortener Service.
Supported by Postgres DB which stores full url path and corresponding short url when any new url is encountered else shortpath url is fetched and shown.
Redirect is done by AJAX call to backend.

PostgresTable Entries

 lid |                    urlpath                     |   trmurl    |       created_on
-----+------------------------------------------------+-------------+-------------------------
   1 | https://in.via.com/hotels/search/bbj45         | h://i.vc/1d | 2021-11-16 15:22:14.106
   6 | https://www.cybrilla.com/newService/04         | h://w.cc/1i | 2021-11-16 22:45:58.443
   7 | https://www.cybrilla.com/newService/045        | h://w.cc/1j | 2021-11-16 23:14:07.041
   8 | https://www.cybrilla.com/newService/04/prateek | h://w.cc/1k | 2021-11-16 23:38:14.187
