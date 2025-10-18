import { useState } from 'react';
import './App.css'

function App() {
  const [longUrl, setLongUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');
  const [error, setError] = useState(null);
  const [copySuccess, setCopySuccess] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setShortUrl('');
    setCopySuccess('');

    try {
      const res = await fetch('http://localhost:8080/api/shorten', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ longUrl }),
      });

      if (!res.ok) throw new Error('Failed to shorten URL');

      const data = await res.json();
      setShortUrl(`http://localhost:8080/api/original/${data.shortCode}`);
    } catch (err) {
      setError(err.message);
    }
  };


   const copyToClipboard = () => {
    if (!shortUrl) return;
    navigator.clipboard.writeText(shortUrl).then(() => {
      setCopySuccess(`Copied: ${shortUrl}`);
      setTimeout(() => setCopySuccess(''), 3000); // Hide message after 3 sec
    }).catch(() => {
      setCopySuccess('Failed to copy');
      setTimeout(() => setCopySuccess(''), 3000);
    });
  };

  return (
    <div style={{ maxWidth: 600, margin: '2rem auto', fontFamily: 'sans-serif' }}>
      <h1>URL Shortener</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="url"
          placeholder="Enter long URL"
          value={longUrl}
          onChange={(e) => setLongUrl(e.target.value)}
          required
          style={{ width: '100%', padding: '0.5em', marginBottom: '1em' }}
        />
        <button type="submit">Shorten URL</button>
      </form>

      {shortUrl && (
        <div style={{ marginTop: '1em' }}>
          <p>
            Short URL: <a href={shortUrl} target="_blank" rel="noreferrer">{shortUrl}</a>
          </p>
          <button onClick={copyToClipboard}>Copy</button>
          {copySuccess && <span style={{ marginLeft: '1em' }}>{copySuccess}</span>}
        </div>
      )}

      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
}

export default App;
