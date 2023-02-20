<%
            class LoginTimeoutException extends Exception {

                String value;

                public String toString() {
                    return "LoginTimeoutException: " + value;
                }

			   public LoginTimeoutException(String errorMessage) {
					super(errorMessage);
					value = errorMessage;
				}
            }

            String sa4ck = (String)request.getSession().getAttribute("sa4ck");
			
           try {
                if (!sa4ck.equals("")) {
                    String sa4ckDecr = new it.finmatica.sa4.crypto.SanCrypter().decryptB64(sa4ck);
                    long before = Long.parseLong(sa4ckDecr.substring(sa4ckDecr.indexOf("#") + 1));
					
                    long age = (System.currentTimeMillis() - before) / 1000; //durata in secondi
                    if (age > (120)) {
                        throw new LoginTimeoutException("Timeout nel login: "+age+" "+before+" "+System.currentTimeMillis());
                    }
                } else {
                   throw new Exception("ck null");
                }
				//request.getSession().removeAttribute("sa4ck");
                response.sendRedirect(request.getContextPath() + "/ad4web/AD4Ambiente.do");
            } catch (LoginTimeoutException to) {
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/common/Sa4Error.jsp?e=" + to.toString() );
            } catch (Exception tt) {
                out.println(tt);
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/common/Sa4Error.jsp?e=Rilevato attacco CSRF (" + sa4ck + ") "+tt.getMessage());
            }

%>
